package com.example.practice.controller;

import com.example.practice.entity.OrderItem;
import com.example.practice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<OrderItem> orderItems(
            @PageableDefault(size = 20, sort= "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return orderItemService.getAll(pageable);
    }
}
