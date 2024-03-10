package com.example.orderTracking.exceptions.runtimeExceptions.NotSuitableException;

public class UserDoesNotHaveACardInfoException extends NotSuitableException{
    private static final long serialVersionUID = 1;

    public UserDoesNotHaveACardInfoException(String message) {
        super(message);
    }
}
