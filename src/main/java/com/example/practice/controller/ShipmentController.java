package com.example.practice.controller;

import com.example.practice.entity.Shipment;
import com.example.practice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/shipments/{id}")
    public Shipment shipment(@PathVariable Long id) {
        return shipmentService.getById(id);
    }
    @GetMapping("/shipments")
    public List<Shipment> shipments() {
        return shipmentService.getAll();
    }
}
