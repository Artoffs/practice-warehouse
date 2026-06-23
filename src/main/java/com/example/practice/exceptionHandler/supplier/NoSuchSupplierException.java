package com.example.practice.exceptionHandler.supplier;

public class NoSuchSupplierException extends RuntimeException{
    public NoSuchSupplierException(String message) {
        super(message);
    }
}
