package com.example.todolist.web;

import com.example.todolist.domain.Todo;
import com.example.todolist.dto.TodoRequest;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;


    @PatchMapping("/api/todos/{id}")
    public ResponseEntity<Todo> completeStatusChange(@PathVariable Long id) {
        return todoService.updateTodoStatus(id).map(todo ->
                        ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());

    }


    @DeleteMapping("/api/todos/completed")
    public ResponseEntity<Todo> deleteCompletedTodos() {
        int delCnt = todoService.clearCompletedTodos();
            return delCnt > 0 ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
    }

    @PostMapping("/api/todos/maketodo")
    public ResponseEntity<Todo> makeTodo(@RequestBody TodoRequest todo){
        return todoService.makeTodo(todo.content()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
