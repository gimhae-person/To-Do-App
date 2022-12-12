package com.ToDoApplication.TodoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TodoPatchDto {

    private long todoId;

    private String title;

    private int todo_order;

    private boolean completed;

    public void setTodoId(long todoId) {this.todoId = todoId; }
}
