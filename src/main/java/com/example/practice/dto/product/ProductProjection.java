package com.example.practice.dto.product;

import java.math.BigDecimal;

public record ProductProjection(
        Long id,
        String supplierName,
        String productName,
        String description,
        BigDecimal price
) {
}
