package com.example.userservice.exception;

import com.example.userservice.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ServiceException extends RuntimeException{
    private static final ErrorType errorType = ErrorType.FATAL_ERROR_TYPE;

    public ServiceException() {
    }

    public ServiceException(String message) {
    super(message);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
