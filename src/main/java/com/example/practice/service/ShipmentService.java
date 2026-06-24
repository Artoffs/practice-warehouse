package com.example.practice.service;

import com.example.practice.dao.ShipmentRepository;
import com.example.practice.dto.shipment.ShipmentProjection;
import com.example.practice.entity.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Page<ShipmentProjection> getAll(Pageable pageable){
        return shipmentRepository.findAllProjections(pageable);
    }

    public Shipment getById(Long id) {
        Optional<Shipment> byId = shipmentRepository.findById(id);
        return byId.orElseThrow();
    }
}
