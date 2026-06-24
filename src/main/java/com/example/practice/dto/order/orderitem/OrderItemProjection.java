package com.example.practice.dto.order.orderitem;

import java.math.BigDecimal;

public record OrderItemProjection(
        Long orderId,
        Long productId,
        Integer quantity,
        BigDecimal priceAtShipment
) {
}
