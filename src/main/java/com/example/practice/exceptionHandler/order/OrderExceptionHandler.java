package com.example.practice.exceptionHandler.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderIncorrectData> handleException(NoSuchOrderException e) {
        OrderIncorrectData data = new OrderIncorrectData();
        data.setInfo(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }

}
