package com.example.practice.controller;

import com.example.practice.entity.Order;
import com.example.practice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{id}")
    public Order order(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/orders")
    public List<Order> orders() {
        return orderService.getAll();
    }
}
