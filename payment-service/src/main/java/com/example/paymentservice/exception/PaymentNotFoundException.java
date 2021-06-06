package com.example.paymentservice.exception;

import com.example.paymentservice.model.enums.ErrorType;

public class PaymentNotFoundException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Payment is not found";


    public PaymentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PaymentNotFoundException(String message){
        super(message);
    }

    @Override
    public ErrorType getErrorType(){
        return ErrorType.DATABASE_ERROR_TYPE;
    }

}
