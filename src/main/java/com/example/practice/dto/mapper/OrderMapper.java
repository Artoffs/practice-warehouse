package com.example.practice.dto.mapper;

import com.example.practice.dto.order.OrderItemResponse;
import com.example.practice.dto.order.OrderResponse;
import com.example.practice.entity.Order;
import com.example.practice.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    /**
     * Order → OrderResponse
     */
    public OrderResponse toOrderResponse(Order order) {
        if (order == null) {
            return null;
        }

        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(this::toOrderItemResponse)
                .collect(Collectors.toList());

        return new OrderResponse(
                itemResponses,
                order.getTotalPrice().doubleValue()
        );
    }

    /**
     * OrderItem → OrderItemResponse
     */
    public OrderItemResponse toOrderItemResponse(OrderItem item) {
        if (item == null) {
            return null;
        }

        return new OrderItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPriceAtShipment().doubleValue(),
                item.getQuantity() * item.getPriceAtShipment().doubleValue()  // totalPrice = quantity * price
        );
    }

    /**
     * Список Order → список OrderResponse
     */
    public List<OrderResponse> toOrderResponseList(List<Order> orders) {
        if (orders == null) {
            return List.of(); // пустой список
        }
        return orders.stream()
                .map(this::toOrderResponse)
                .collect(Collectors.toList());
    }
}