package com.example.practice.dto.order;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        List<OrderItemResponse> orderItems,
        LocalDate shipmentDate,
        Double totalPrice
){}
