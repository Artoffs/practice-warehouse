package com.example.practice.controller;

import com.example.practice.entity.PickupPoint;
import com.example.practice.service.PickUpPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pickuppoints")
@RequiredArgsConstructor
public class PickUpPointController {

    private final PickUpPointService pickUpPointService;

    @GetMapping("/{id}")
    public PickupPoint getById(@PathVariable Long id) {
        return pickUpPointService.findByIdOrThrow(id);
    }
    @GetMapping
    public List<PickupPoint> getAll() {
        return pickUpPointService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PickupPoint save(@RequestBody PickupPoint pickupPoint) {
        return pickUpPointService.save(pickupPoint);
    }
}
