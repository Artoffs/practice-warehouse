package com.example.practice.service;

import com.example.practice.dao.OrderItemRepository;
import com.example.practice.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    // Когда появятся дто перепишу с использованием дто
    public Page<OrderItem> getAll(Pageable pageable){
        return orderItemRepository.getAllWithDeps(pageable);
    }
}
