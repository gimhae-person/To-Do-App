package com.ToDoApplication.TodoApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private int todo_order;

    @Column(nullable = false)
    private boolean completed = false;

    public Todos(String title) {this.title = title;}

    public Todos(String title, int todo_order) {
        this.title = title;
        this.todo_order = todo_order;
    }

    public Todos(String title, int todo_order, boolean completed) {
        this.title = title;
        this.todo_order = todo_order;
        this.completed = completed;
    }
}
