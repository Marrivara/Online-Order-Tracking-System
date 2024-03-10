package com.example.orderTracking.exceptions.runtimeExceptions.notFoundException;

public class CardInfoNotFoundException extends NotFoundException{
    private static final long serialVersionUID = 1;

    public CardInfoNotFoundException(String message) {
        super(message);
    }
}
