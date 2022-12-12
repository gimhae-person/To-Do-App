package com.ToDoApplication.TodoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private int todo_order;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long todoId;
        private String title;
        private int todo_order;
        private boolean completed;

        public void setTodoId(long todoId) {
            this.todoId = todoId;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long todoId;
        private String title;
        private int todo_order;
        private boolean completed;
    }
}
