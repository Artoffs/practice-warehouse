package com.example.practice.service;

import com.example.practice.dao.PickupPointRepository;
import com.example.practice.entity.PickupPoint;
import com.example.practice.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PickUpPointService {

    private final PickupPointRepository pickupPointRepository;

    @Autowired
    public PickUpPointService(PickupPointRepository pickupPointRepository) {
        this.pickupPointRepository = pickupPointRepository;
    }

    public PickupPoint findByIdOrThrow(Long id) {
        Optional<PickupPoint> byId = pickupPointRepository.findById(id);
        return byId.orElseThrow(() ->
                new ResourceNotFoundException("Пункт выдачи", id));
    }

    public Optional<PickupPoint> findById(Long id) {
        return pickupPointRepository.findById(id);
    }

    public List<PickupPoint> getAll() {
        return pickupPointRepository.findAll();
    }

    public PickupPoint save(PickupPoint pickupPoint) {
        return pickupPointRepository.save(pickupPoint);
    }
}
