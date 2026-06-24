package com.example.practice.service;

import com.example.practice.dao.PickupPointRepository;
import com.example.practice.entity.PickupPoint;
import com.example.practice.exceptionHandler.pickuppoint.NoSuchPickUpPointException;
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

    public PickupPoint getById(Long id) {
        Optional<PickupPoint> byId = pickupPointRepository.findById(id);
        return byId.orElseThrow(() ->
                new NoSuchPickUpPointException("Пункт выдачи с таким айди не найден"));
    }

    public List<PickupPoint> getAll() {
        return pickupPointRepository.findAll();
    }
}
