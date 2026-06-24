package com.example.practice.dto.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchSupplierRequest (
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        String phone
) {
}
