package com.example.practice.service;

import com.example.practice.dao.OrderItemRepository;
import com.example.practice.dao.OrderRepository;
import com.example.practice.dto.mapper.OrderMapper;
import com.example.practice.dto.order.CreateOrderRequest;
import com.example.practice.dto.order.OrderProjection;
import com.example.practice.dto.order.OrderResponse;
import com.example.practice.dto.order.orderitem.OrderItemRequest;
import com.example.practice.entity.Order;
import com.example.practice.entity.OrderItem;
import com.example.practice.entity.Product;
import com.example.practice.exceptionHandler.InvalidReferenceException;
import com.example.practice.exceptionHandler.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderMapper orderMapper;

    public Page<OrderProjection> getAll(Pageable pageable){
        return orderRepository.findAllProjections(pageable);
    }

    public OrderResponse findByIdOrThrow(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Заказ", id));
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }


    public OrderResponse save(CreateOrderRequest request) {

        Order order = new Order();

        List<OrderItem> list = request.items()
                .stream()
                .map(orderItemRequest -> {
                    OrderItem orderItem = createOrderItem(orderItemRequest);
                    order.addItem(orderItem);
                return orderItem;})
                .toList();

        order.setTotalPrice(list
                .stream()
                .map(OrderItem::getPriceAtShipment)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        orderRepository.save(order);
        orderItemRepository.saveAll(list);

        return orderMapper.toOrderResponse(order);
    }

    private OrderItem createOrderItem(OrderItemRequest orderItemRequest) {

        Product product = productService
                .findById(orderItemRequest.productId())
                .orElseThrow(() ->
                        new InvalidReferenceException(
                                "продукт",
                                orderItemRequest.productId()));

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemRequest.quantity());
        orderItem.setPriceAtShipment(product.getPrice());
        orderItem.setProduct(product);

        return orderItem;
    }
}
