package com.example.practice.dto;

import java.math.BigDecimal;

public record OrderItemProjection(
        Long orderId,
        Long productId,
        Integer quantity,
        BigDecimal priceAtShipment
) {
}
