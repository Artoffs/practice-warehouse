package com.example.practice.kafka.event;

import java.math.BigDecimal;

public record ProductCreatedEvent (
        Long id,
        String name,
        BigDecimal price
) {
}
