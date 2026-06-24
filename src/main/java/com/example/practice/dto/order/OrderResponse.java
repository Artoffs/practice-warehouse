package com.example.practice.dto.order;


import com.example.practice.dto.order.orderitem.OrderItemResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        List<OrderItemResponse> orderItems,
        BigDecimal totalPrice
){}
