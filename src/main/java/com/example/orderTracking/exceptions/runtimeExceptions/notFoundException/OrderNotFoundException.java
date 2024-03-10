package com.example.orderTracking.exceptions.runtimeExceptions.notFoundException;

public class OrderNotFoundException extends NotFoundException{
    private static final long serialVersionUID = 1;

    public OrderNotFoundException(String message) {
        super(message);
    }
}
