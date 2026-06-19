package com.example.practice.controller;

import com.example.practice.entity.OrderItem;
import com.example.practice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderitems/{id}")
    public OrderItem orderItem(@PathVariable Long id) {
        return orderItemService.getById(id);
    }

    @GetMapping("/orderitems")
    public List<OrderItem> orderItems() {
        return orderItemService.getAll();
    }
}
