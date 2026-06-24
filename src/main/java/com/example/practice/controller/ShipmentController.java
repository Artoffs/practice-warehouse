package com.example.practice.controller;

import com.example.practice.dto.shipment.ShipmentProjection;
import com.example.practice.entity.Shipment;
import com.example.practice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping("/{id}")
    public Shipment getById(@PathVariable Long id) {
        return shipmentService.getById(id);
    }

    @GetMapping
    public Page<ShipmentProjection> getAll(
            @PageableDefault(sort="id")Pageable pageable) {
        return shipmentService.getAll(pageable);
    }
}
