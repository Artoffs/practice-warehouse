package com.example.practice.dto.shipment;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateShipmentRequest(
        @NotNull(message = "Не указан айди пункта выдачи")
        Long pickUpPointId,
        @NotNull(message = "Не указаны айди заказов")
        List<Long> orderId
) {
}
