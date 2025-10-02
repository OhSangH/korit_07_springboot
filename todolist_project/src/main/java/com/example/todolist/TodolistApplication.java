package com.example.todolist;

import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import com.example.todolist.domain.Todo;
import com.example.todolist.domain.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class TodolistApplication implements CommandLineRunner {
    private final AppUserRepository appUserRepository;
    private final TodoRepository todoRepository;


	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        AppUser appUser1 = new AppUser("user", "$2a$12$qjEcqrjxB9b5UidCOhO2NeEF8Ty72GtaMBW3jc4kw/vJDgFAvPG4W", "USER");
        AppUser appUser2 = new AppUser("admin", "$2a$12$t8F72s6KaIxeMpbP9mjB/.jEA0YCzOLE8AxcZv3gDxNhkV5bDP2CK", "ADMIN");
        appUserRepository.saveAll(Arrays.asList(appUser1, appUser2));

        Todo todo1 = new Todo("study1", appUser1);
        Todo todo2 = new Todo("study2", appUser1);
        Todo todo3 = new Todo("study3", appUser1);

        todoRepository.saveAll(Arrays.asList(todo1, todo2, todo3));

    }
}
