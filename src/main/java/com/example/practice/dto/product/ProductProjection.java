package com.example.practice.dto.product;

import java.math.BigDecimal;

public record ProductProjection(
        String supplierName,
        String productName,
        String description,
        BigDecimal price
) {
}
