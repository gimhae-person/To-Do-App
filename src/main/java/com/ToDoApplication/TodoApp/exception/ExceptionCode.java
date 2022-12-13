package com.ToDoApplication.TodoApp.exception;

import lombok.Getter;

public enum ExceptionCode {
    TO_DO_EXIST(409, "Todo exists"),
    TO_DO_TITLE_EXIST(409, "Todo title exists"),
    TO_DO_NOT_FOUND(404, "Todo not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
