package com.example.practice.dto.order.orderitem;

public record OrderItemResponse(
        Long productId,
        String productName,
        Integer quantity,
        Double price,
        Double totalPrice
){}
