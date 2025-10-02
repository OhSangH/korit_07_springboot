package com.example.todolist.service;

import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final AppUserRepository appUserRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public Optional<Todo> updateTodoStatus(Long id) {
        return todoRepository.findById(id).map(todo -> {
            todo.setComplete(!todo.isComplete());
            return todo;
        });
    }

    @Transactional
    public int clearCompletedTodos() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.isEmpty() || username.equals("anonymousUser")) {
            return 0;
        }
        AppUser appUser = appUserRepository.findByUsername(username).orElse(null);
        if (appUser != null) {
            List<Todo> todos = appUser.getTodos();
            if (!todos.isEmpty()) {
                int cnt = 0;
                for (Todo todo : new ArrayList<>(todos)) {
                    if (todo.isComplete()) {
                        todoRepository.delete(todo);
                        todos.remove(todo);
                        cnt++;
                    }
                }
                return cnt;
            }
        }
        return 0;
    }

    public boolean makeTodo(String content) {
        Optional<AppUser> user = appUserRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent()) {
            Todo todo = new Todo(content, user.get());
            todoRepository.save(todo);
            return true;
        }
        return false;
    }

}
