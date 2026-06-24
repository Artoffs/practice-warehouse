package com.example.practice.service;

import com.example.practice.dao.OrderItemRepository;
import com.example.practice.dto.order.orderitem.OrderItemProjection;
import com.example.practice.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;


    public OrderItem getById(Long id) {
        Optional<OrderItem> byId = orderItemRepository.findById(id);
        return byId.orElseThrow();
    }

    // Когда появятся дто перепишу с использованием дто
    public Page<OrderItemProjection> getAll(Pageable pageable){
        return orderItemRepository.findAllProjections(pageable);
    }
}
