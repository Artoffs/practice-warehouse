package com.example.practice.dto.shipment;

import com.example.practice.entity.enums.ShipmentStatus;

import java.time.LocalDate;

public record ShipmentProjection (
        Long id,
        String pickUpPointAddress,
        ShipmentStatus status,
        LocalDate createdAt
) {
}
