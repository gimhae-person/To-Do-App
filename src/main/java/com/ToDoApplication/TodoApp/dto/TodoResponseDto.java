package com.ToDoApplication.TodoApp.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodoResponseDto {
    private long todoId;
    private String title;
    private int todo_order;
    private boolean completed;
}
