package com.example.practice.exceptionHandler.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.practice.controller")
public class ProductExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> handleException(NoSuchProductException e) {
        ProductIncorrectData data = new ProductIncorrectData();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
