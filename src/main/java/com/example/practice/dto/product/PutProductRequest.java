package com.example.practice.dto.product;

import java.math.BigDecimal;

public record PutProductRequest(
        Long supplierId,
        String name,
        String description,
        BigDecimal price) {
}
