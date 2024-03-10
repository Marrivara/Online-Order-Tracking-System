package com.example.orderTracking.exceptions.runtimeExceptions.notFoundException;

public class ProductNotFoundException extends NotFoundException{
    private static final long serialVersionUID = 1;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
