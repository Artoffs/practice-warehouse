package com.example.practice.exceptionHandler.shipment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShipmentExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handle(NoSuchShipmentException e) {
        ShipmentIncorrectData data = new ShipmentIncorrectData();
        data.setData(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }

}
