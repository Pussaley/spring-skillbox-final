package com.example.hotelbooking.web.controller.exception;

import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.web.dto.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class HotelBookingMainExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException exception) {
        final String message = exception.getMessage();

        log.debug("EntityNotFoundException.class, timestamp: {}", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(message)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> badRequest(MethodArgumentNotValidException exception) {
        final String message = exception.getMessage();

        log.debug("MethodArgumentNotValidException.class, timestamp: {}", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(message)
        );
    }

/*    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> internal(Throwable throwable) {
        final String message = throwable.getMessage();

        log.debug("Internal Server Error by Throwable.class: {}, timestamp: {}", throwable.getCause().getClass().getName(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(message)
        );
    }*/

}