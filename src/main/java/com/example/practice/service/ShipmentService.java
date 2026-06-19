package com.example.practice.service;

import com.example.practice.dao.ShipmentRepository;
import com.example.practice.entity.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> getAll(){
        return shipmentRepository.findAllWithDependencies();
    }

    public Shipment getById(Long id) {
        Optional<Shipment> byId = shipmentRepository.findById(id);
        return byId.orElseThrow();
    }
}
