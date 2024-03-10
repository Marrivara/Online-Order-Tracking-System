package com.example.orderTracking.exceptions.runtimeExceptions.notFoundException;

public class UserNotFoundException extends NotFoundException{
    private static final long serialVersionUID = 1;

    public UserNotFoundException(String message) {
        super(message);
    }
}
