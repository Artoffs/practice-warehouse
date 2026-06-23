package com.example.practice.dto.order;

import java.util.List;

public record OrderCreateRequest(
        List<OrderItemRequest> items
) {
}
