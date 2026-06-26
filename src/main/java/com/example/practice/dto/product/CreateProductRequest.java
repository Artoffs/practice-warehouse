package com.example.practice.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotNull(message = "Поставщик обязателен")
        Long supplierId,
        @NotBlank(message = "Укажите название товара")
        String name,
        @NotBlank
        String description,
        @Positive(message = "Цена не может быть отрицательной")
        BigDecimal price
) {
}
