package com.example.practice.service;

import com.example.practice.dao.OrderRepository;
import com.example.practice.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return orderRepository.findAllWithDependencies();
    }

    public Order getById(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.orElseThrow();
    }
}
