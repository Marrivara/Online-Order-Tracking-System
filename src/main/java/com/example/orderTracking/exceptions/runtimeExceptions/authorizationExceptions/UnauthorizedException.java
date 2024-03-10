package com.example.orderTracking.exceptions.runtimeExceptions.authorizationExceptions;

public class UnauthorizedException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public UnauthorizedException(String message) {
        super(message);
    }
}
