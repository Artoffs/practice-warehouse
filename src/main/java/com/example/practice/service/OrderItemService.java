package com.example.practice.service;

import com.example.practice.dao.OrderItemRepository;
import com.example.practice.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem getById(Long id) {
        Optional<OrderItem> byId = orderItemRepository.findById(id);
        return byId.orElseThrow();
    }

    public List<OrderItem> getAll(){
        return orderItemRepository.findAll();
    }
}
