package com.example.practice.dto.order;

import com.example.practice.entity.enums.ShipmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderProjection (
        Long orderId,
        BigDecimal totalPrice,
        ShipmentStatus shipmentStatus,
        LocalDate shipmentCreatedAt,
        String pickupPointAddress
) {
}
