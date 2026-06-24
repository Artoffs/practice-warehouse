package com.example.practice.exceptionHandler.pickuppoint;

public class NoSuchPickUpPointException extends RuntimeException {
    public NoSuchPickUpPointException(String message) {
        super(message);
    }
}
