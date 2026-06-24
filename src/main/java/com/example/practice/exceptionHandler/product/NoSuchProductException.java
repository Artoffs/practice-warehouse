package com.example.practice.exceptionHandler.product;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        super(message);
    }
}
