package com.cardservice.exception;

import com.cardservice.model.enums.ErrorType;

public class CardNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Card is not found";


    public CardNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CardNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }

}
