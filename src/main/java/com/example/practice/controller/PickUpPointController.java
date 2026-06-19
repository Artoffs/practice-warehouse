package com.example.practice.controller;

import com.example.practice.entity.PickupPoint;
import com.example.practice.service.PickUpPointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PickUpPointController {

    private final PickUpPointService pickUpPointService;

    public PickUpPointController(PickUpPointService pickUpPointService) {
        this.pickUpPointService = pickUpPointService;
    }

    @GetMapping("/pickuppoints/{id}")
    public PickupPoint pickupPoint(@PathVariable Long id) {
        return pickUpPointService.getById(id);
    }
    @GetMapping("/pickuppoints")
    public List<PickupPoint> pickupPoints() {
        return pickUpPointService.getAll();
    }
}
