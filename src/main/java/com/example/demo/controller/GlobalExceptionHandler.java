package com.example.demo.controller;

import com.example.demo.dto.Details;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.exception.WebAppException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ResponseStatus
    @ExceptionHandler(WebAppException.class)
    public ErrorResponse handleEntityAlreadyExistsException(WebAppException exception, HttpServletRequest request) {
       return ErrorResponse.builder()
                .title(exception.getTitle())
                .source(request.getRequestURI())
                .localDateTime(LocalDateTime.now())
                .details(exception.getDetails())
                .build();
    }

    @ResponseStatus
    @ExceptionHandler(TypeMismatchException.class)
    public ErrorResponse handleTypeMismatchException(TypeMismatchException exception, HttpServletRequest request) {
        return ErrorResponse.builder()
                .title("Argument's type passed to method is not correct")
                .source(request.getRequestURI())
                .localDateTime(LocalDateTime.now())
                .details(new Details("Type Mismatch", exception.getMessage()))
                .build();
    }

    @ResponseStatus
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> handleTypeMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        return exception.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    return ErrorResponse.builder()
                            .title("Argument's type passed to method is not correct")
                            .source(request.getRequestURI())
                            .localDateTime(LocalDateTime.now())
                            .details(new Details(((FieldError) objectError).getField(), objectError.getDefaultMessage()))
                            .build();
                })
                .collect(Collectors.toList());
    }

    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public  ErrorResponse handleCommonException(Exception exception, HttpServletRequest request) {
       return ErrorResponse.builder()
                .title("Incorrect datatype of passed argument")
                .source(request.getRequestURI())
                .localDateTime(LocalDateTime.now())
                .details(new Details("Invalid argument", exception.getMessage()))
                .build();

    }
}
