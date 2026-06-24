package com.example.practice.controller;

import com.example.practice.dto.order.orderitem.OrderItemProjection;
import com.example.practice.entity.OrderItem;
import com.example.practice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderitems")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public OrderItem getById(@PathVariable Long id) {
        return orderItemService.getById(id);
    }

    @GetMapping
    public Page<OrderItemProjection> orderItems(
            @PageableDefault(size = 20, sort= "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return orderItemService.getAll(pageable);
    }
}
