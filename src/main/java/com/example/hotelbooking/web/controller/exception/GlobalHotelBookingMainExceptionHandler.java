package com.example.hotelbooking.web.controller.exception;

import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.web.dto.error.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class GlobalHotelBookingMainExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFound(EntityNotFoundException exception) {
        final String message = exception.getMessage();
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        log.debug("EntityNotFoundException.class, timestamp: {}", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(
                new ExceptionResponse(httpStatus.value(), message)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> badRequest(MethodArgumentNotValidException exception) {
        final String message = exception.getMessage();
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.debug("MethodArgumentNotValidException.class, timestamp: {}", LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(
                new ExceptionResponse(httpStatus.value(), message)
        );
    }

}