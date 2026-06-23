package com.example.practice.dto.order;

public record OrderItemResponse(
        Long productId,
        String productName,
        Integer quantity,
        Double price,
        Double totalPrice
){}
