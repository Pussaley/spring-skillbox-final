package com.example.hotelbooking.web.controller.exception;

import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.web.dto.error.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalHotelBookingMainExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFound(EntityNotFoundException exception) {

        final String message = exception.getMessage();
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus).body(
                new ExceptionResponse(message, httpStatus.value())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> badRequest(MethodArgumentNotValidException exception) {

        final String message = exception.getMessage();
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(
                new ExceptionResponse(message, httpStatus.value())
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> forbidden(AccessDeniedException exception) {

        final String message = "Доступ запрещён: недостаточно прав";
        final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        return ResponseEntity.status(httpStatus)
                .body(new ExceptionResponse(message, httpStatus.value()));
    }

}