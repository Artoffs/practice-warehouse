package com.example.practice.exceptionHandler.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderIncorrectData> handleException(NoSuchOrderException e) {
        OrderIncorrectData orderInvalidData = new OrderIncorrectData();
        orderInvalidData.setInfo(e.getMessage());
        return new ResponseEntity<>(orderInvalidData, HttpStatus.BAD_REQUEST);
    }

}
