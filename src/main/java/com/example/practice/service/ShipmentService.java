package com.example.practice.service;

import com.example.practice.dao.OrderRepository;
import com.example.practice.dao.ShipmentRepository;
import com.example.practice.dto.mapper.ShipmentMapper;
import com.example.practice.dto.shipment.CreateShipmentRequest;
import com.example.practice.dto.shipment.ShipmentProjection;
import com.example.practice.dto.shipment.ShipmentResponse;
import com.example.practice.entity.Order;
import com.example.practice.entity.PickupPoint;
import com.example.practice.entity.Shipment;
import com.example.practice.exceptionHandler.InvalidReferenceException;
import com.example.practice.exceptionHandler.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final PickUpPointService pickUpPointService;
    private final ShipmentMapper mapper;
    private final OrderRepository orderRepository;

    public Page<ShipmentProjection> findAll(Pageable pageable){
        return shipmentRepository.findAllProjections(pageable);
    }

    public ShipmentResponse findByIdOrThrow(Long id) {
        Optional<Shipment> byId = shipmentRepository.findById(id);
        return byId.map(mapper::toResponse).orElseThrow(() ->
                new ResourceNotFoundException("Доставка", id));
    }

    public Optional<Shipment> findById(Long id) {
        return shipmentRepository.findById(id);
    }

    @Transactional
    public ShipmentResponse save(CreateShipmentRequest request) {
        PickupPoint pickupPoint = pickUpPointService.findById(request.pickUpPointId())
                .orElseThrow(() ->
                        new InvalidReferenceException("пункт выдачи", request.pickUpPointId()));

        List<Order> orders = orderRepository.findAllById(request.orderId());
        if (orders.size() != request.orderId().size()) {
            throw new InvalidReferenceException("Некоторые заказы не найдены");
        }

        Shipment entity = mapper.toEntity(request);
        entity.setPickUpPoint(pickupPoint);
        orders.forEach(order -> order.setShipment(entity));
        entity.setOrders(orders);

        Shipment savedShipment = shipmentRepository.save(entity);

        return mapper.toResponse(savedShipment);
    }
}
