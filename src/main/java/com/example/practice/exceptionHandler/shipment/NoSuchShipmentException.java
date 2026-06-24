package com.example.practice.exceptionHandler.shipment;

public class NoSuchShipmentException extends RuntimeException {
    public NoSuchShipmentException(String message) {
        super(message);
    }
}
