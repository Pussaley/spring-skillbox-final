package com.example.hotelbooking.web.controller.exception;

import com.example.hotelbooking.domain.exception.EntityNotFoundException;
import com.example.hotelbooking.web.dto.error.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> httpMethodNotSupported(HttpRequestMethodNotSupportedException exception) {

        final String message = "Доступ запрещён: Метод не поддерживается";
        final HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;

        return ResponseEntity.status(httpStatus)
                .body(new ExceptionResponse(message, httpStatus.value()));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> notAccessible(Throwable exception) {

        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        final String errorCode = UUID.randomUUID().toString();
        final String message = "Произошла неизвестная ошибка, обратитесь к администратору портала и назовите код ошибки: ".concat(errorCode);

        return ResponseEntity.status(httpStatus)
                .body(new ExceptionResponse(message, httpStatus.value()));
    }

}