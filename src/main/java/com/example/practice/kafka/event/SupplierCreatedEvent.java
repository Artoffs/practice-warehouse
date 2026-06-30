package com.example.practice.kafka.event;

public record SupplierCreatedEvent(
        Long supplierId,
        String name
) {
}
