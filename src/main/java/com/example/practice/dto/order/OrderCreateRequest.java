package com.example.practice.dto.order;

import com.example.practice.dto.order.orderitem.OrderItemRequest;

import java.util.List;

public record OrderCreateRequest(
        List<OrderItemRequest> items
) {
}
