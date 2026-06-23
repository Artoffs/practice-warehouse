package com.example.practice.exceptionHandler.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SupplierExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handeException(NoSuchSupplierException e) {
        SupplierIncorrectData data = new SupplierIncorrectData();
        data.setData(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }
}
