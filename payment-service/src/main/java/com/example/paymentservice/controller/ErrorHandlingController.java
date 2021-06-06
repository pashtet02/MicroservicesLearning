package com.example.paymentservice.controller;


import com.example.paymentservice.exception.CardNotFoundException;
import com.example.paymentservice.exception.PaymentNotFoundException;
import com.example.paymentservice.exception.ServiceException;
import com.example.paymentservice.model.Error;
import com.example.paymentservice.model.enums.ErrorType;
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

    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handlePaymentNotFoundException(PaymentNotFoundException ex) {
        log.error("handlePaymentNotFoundException: message {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.DATABASE_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleCardNotFoundException(CardNotFoundException ex) {
        log.error("handleCardNotFoundException: message {}", ex.getMessage());
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

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handlerRuntimeException(RuntimeException ex) {
        log.error("handlerRuntimeException: message: {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}
