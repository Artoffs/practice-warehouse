package com.example.practice.controller;

import com.example.practice.dto.order.CreateOrderRequest;
import com.example.practice.dto.order.OrderProjection;
import com.example.practice.dto.order.OrderResponse;
import com.example.practice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        return orderService.findByIdOrThrow(id);
    }

    @GetMapping
    public Page<OrderProjection> getAll(@PageableDefault(size = 20)Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        OrderResponse order = orderService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
