package com.cardservice.exception;

import com.cardservice.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ServiceException extends RuntimeException {
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
