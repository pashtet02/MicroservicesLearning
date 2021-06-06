package com.example.paymentservice.exception;

import com.example.paymentservice.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = false)
@Data
public class ClientException extends RuntimeException{
        private static final ErrorType errorType = ErrorType.FATAL_ERROR_TYPE;

        public ClientException() {
        }

        public ClientException(String message) {
            super(message);
        }

        public ErrorType getErrorType() {
            return errorType;
        }
}
