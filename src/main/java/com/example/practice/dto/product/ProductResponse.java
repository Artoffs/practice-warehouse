package com.example.practice.dto.product;

public record ProductResponse(
        Long id,
        String supplierName,
        String productName,
        String description,
        Double price
) {}
