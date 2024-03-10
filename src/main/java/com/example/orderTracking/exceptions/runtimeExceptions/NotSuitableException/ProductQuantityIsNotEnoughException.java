package com.example.orderTracking.exceptions.runtimeExceptions.NotSuitableException;

public class ProductQuantityIsNotEnoughException extends NotSuitableException{
    private static final long serialVersionUID = 1;

    public ProductQuantityIsNotEnoughException(String message) {
        super(message);
    }
}
