package com.example.practice.service;

import com.example.practice.dao.OrderRepository;
import com.example.practice.dto.mapper.OrderMapper;
import com.example.practice.dto.order.OrderResponse;
import com.example.practice.entity.Order;
import com.example.practice.exceptionHandler.order.NoSuchOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getAll(){
        return orderRepository.findAllWithAllDependencies().stream()
                .map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse getById(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new NoSuchOrderException("Order with id=" + id
                        + " not found"));
    }
}
