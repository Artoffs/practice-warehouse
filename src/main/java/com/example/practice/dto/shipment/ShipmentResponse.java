package com.example.practice.dto.shipment;

import com.example.practice.dto.order.OrderResponse;

import java.time.LocalDate;
import java.util.List;

public record ShipmentResponse(
        Long shipmentId,
        Long pickUpPointId,
        List<OrderResponse> orderList,
        String shipmentStatus,
        LocalDate createdAt
) {
}
