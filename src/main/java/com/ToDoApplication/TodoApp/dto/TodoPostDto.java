package com.ToDoApplication.TodoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TodoPostDto {

    private String title;
    private int todo_order;
}
