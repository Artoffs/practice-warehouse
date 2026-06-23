package com.example.practice.dto.product;

public record ProductRequest(
        Long supplierId,
        String name,
        String description,
        Double price
) {
}
