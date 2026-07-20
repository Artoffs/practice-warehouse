package com.example.practice.dto.order;

import com.example.practice.dto.order.orderitem.OrderItemRequest;
import jakarta.validation.Valid;

import java.util.List;

public record CreateOrderRequest(
        List<@Valid OrderItemRequest> items
) {
}
