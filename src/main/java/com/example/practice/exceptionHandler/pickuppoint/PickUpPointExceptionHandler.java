package com.example.practice.exceptionHandler.pickuppoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PickUpPointExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handle(NoSuchPickUpPointException e) {
        PickUpPointIncorrectData data = new PickUpPointIncorrectData();
        data.setData(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }
}
