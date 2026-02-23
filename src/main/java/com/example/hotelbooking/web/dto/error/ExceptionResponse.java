package com.example.hotelbooking.web.dto.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {

    private final String message;
    private final int status;
    private final LocalDateTime timestamp;

    public ExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}