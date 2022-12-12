package com.ToDoApplication.TodoApp.repository;

import com.ToDoApplication.TodoApp.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todos, Long> {
    Optional<Todos> findByTodoId(long todoId);

    Optional<Todos> findByTitle(String title);
}
