package com.example.practice.dto.order;


import java.util.List;

public record OrderResponse(
        List<OrderItemResponse> orderItems,
        Double totalPrice
){}
