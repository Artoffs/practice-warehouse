package com.example.practice.dto.product;

public record ProductResponse(
        String supplierName,
        String productName,
        String description,
        Double price
) {}
