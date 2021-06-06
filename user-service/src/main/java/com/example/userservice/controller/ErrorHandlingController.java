package com.example.userservice.controller;


import com.example.userservice.exception.ServiceException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Error;
import com.example.userservice.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: message {}", ex.getMessage());
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE,
                        LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleUserNotFoundException(UserNotFoundException ex) {
        log.error("handleUserNotFoundException: message {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.DATABASE_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handlerServiceException(ServiceException ex) {
        log.error("handlerServiceException: message: {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.DATABASE_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        log.error("handleUserNotFoundException: message {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.DATABASE_ERROR_TYPE, LocalDateTime.now());
    }
}
