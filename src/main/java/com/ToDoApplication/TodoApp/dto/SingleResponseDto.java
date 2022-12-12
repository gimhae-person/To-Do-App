package com.ToDoApplication.TodoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class SingleResponseDto<T> {
    private T data;
}

