package com.notificationservice.exception;

import com.notificationservice.model.enums.ErrorType;

public class NotValidPhoneNumberException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Phone number is not valid";


    public NotValidPhoneNumberException() {
        super(DEFAULT_MESSAGE);
    }

    public NotValidPhoneNumberException(String message){
        super(message);
    }

    @Override
    public ErrorType getErrorType(){
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
