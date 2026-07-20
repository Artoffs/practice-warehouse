package com.example.practice.controller;

import com.example.practice.dto.shipment.CreateShipmentRequest;
import com.example.practice.dto.shipment.ShipmentProjection;
import com.example.practice.dto.shipment.ShipmentResponse;
import com.example.practice.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping("/{id}")
    public ShipmentResponse getById(@PathVariable Long id) {
        return shipmentService.findByIdOrThrow(id);
    }

    @GetMapping
    public Page<ShipmentProjection> getAll(
            @PageableDefault(sort="id")Pageable pageable) {
        return shipmentService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShipmentResponse save(@Valid @RequestBody CreateShipmentRequest request) {
        return shipmentService.save(request);
    }
}
