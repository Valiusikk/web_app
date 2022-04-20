package com.example.demo.controller;

import com.example.demo.dto.Details;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.exception.WebAppException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebAppException.class)
    public ErrorResponse handleEntityAlreadyExistsException(WebAppException exception, HttpServletRequest request) {
        return ErrorResponse.builder()
                .title(exception.getTitle())
                .source(request.getRequestURI())
                .localDateTime(LocalDateTime.now())
                .details(exception.getDetails())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public ErrorResponse handleTypeMismatchException(TypeMismatchException exception, HttpServletRequest request) {
        return ErrorResponse.builder()
                .title("Argument's type passed to method is not correct")
                .source(request.getRequestURI())
                .localDateTime(LocalDateTime.now())
                .details(new Details("Type Mismatch", exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleTypeMismatchException(Exception exception, HttpServletRequest request) {
        return ErrorResponse.builder()
                .title("Some exception occurred")
                .source(request.getRequestURI())
                .localDateTime(LocalDateTime.now())
                .details(new Details("Invalid argument", exception.getMessage()))
                .build();
    }
}
