package com.example.orderTracking.exceptions.runtimeExceptions.authorizationExceptions;

public class NotAuthorizedToException extends UnauthorizedException {
    private static final long serialVersionUID = 1;

    public NotAuthorizedToException(String message) {
        super(message);
    }
}
