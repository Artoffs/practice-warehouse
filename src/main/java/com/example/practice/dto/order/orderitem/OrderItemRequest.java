package com.example.practice.dto.order.orderitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull
        Long productId,
        @Positive
        Integer quantity
) {
}
