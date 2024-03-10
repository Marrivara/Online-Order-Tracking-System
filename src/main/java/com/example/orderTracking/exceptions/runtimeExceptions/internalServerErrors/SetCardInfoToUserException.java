package com.example.orderTracking.exceptions.runtimeExceptions.internalServerErrors;

public class SetCardInfoToUserException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public SetCardInfoToUserException(String message) {
        super(message);
    }
}
