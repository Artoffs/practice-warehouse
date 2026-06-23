package com.example.practice.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotNull(message = "Поставщик обязателен")
        Long supplierId,
        @NotBlank(message = "Укажите название товара")
        String name,
        @NotBlank
        String description,
        @Positive(message = "Цена не может быть отрицательной")
        Double price
) {
}
