package com.example.practice.dto.mapper;

import com.example.practice.dto.order.OrderResponse;
import com.example.practice.dto.shipment.CreateShipmentRequest;
import com.example.practice.dto.shipment.ShipmentResponse;
import com.example.practice.entity.PickupPoint;
import com.example.practice.entity.Shipment;
import com.example.practice.entity.enums.ShipmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShipmentMapper {

    private final OrderMapper orderMapper;

    /**
     * Преобразует CreateShipmentRequest в сущность Shipment
     */
    public Shipment toEntity(CreateShipmentRequest request) {
        if (request == null) {
            return null;
        }

        Shipment shipment = new Shipment();
        shipment.setStatus(ShipmentStatus.CREATED); // дефолтный статус

        return shipment;
    }

    /**
     * Преобразует сущность Shipment в ShipmentResponse
     */
    public ShipmentResponse toResponse(Shipment shipment) {
        if (shipment == null) {
            return null;
        }

        Long shipmentId = shipment.getId();
        Long pickUpPointId = Long.valueOf(shipment.getPickUpPoint() != null
                ? shipment.getPickUpPoint().getId()
                : null);

        List<OrderResponse> orders = shipment.getOrders() != null
                ? shipment.getOrders().stream().map(orderMapper::toOrderResponse).toList()
                : new ArrayList<>();


        String status = shipment.getStatus() != null
                ? shipment.getStatus().name()
                : null;

        LocalDate createdAt = shipment.getCreatedAt();

        return new ShipmentResponse(
                shipmentId,
                pickUpPointId,
                orders,
                status,
                createdAt
        );
    }

    /**
     * Преобразует список сущностей в список ShipmentResponse
     */
    public List<ShipmentResponse> toResponseList(List<Shipment> shipments) {
        if (shipments == null) {
            return new ArrayList<>();
        }

        return shipments.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Обновляет существующую сущность Shipment из запроса (если нужно для PUT/PATCH)
     */
    public void updateEntity(Shipment shipment, CreateShipmentRequest request, PickupPoint pickUpPoint) {
        if (shipment == null || request == null) {
            return;
        }

        if (pickUpPoint != null) {
            shipment.setPickUpPoint(pickUpPoint);
        }
    }
}