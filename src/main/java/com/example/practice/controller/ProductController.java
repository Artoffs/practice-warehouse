package com.example.practice.controller;

import com.example.practice.dto.product.PatchProductRequest;
import com.example.practice.dto.product.ProductProjection;
import com.example.practice.dto.product.CreateProductRequest;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.entity.Product;
import com.example.practice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Потенциальная проблема с использованием номера страницы, которая не существует
    @GetMapping
    public Page<ProductProjection> products(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        return productService.getAll(pageable);
        }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse save = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody PatchProductRequest request) {
        ProductResponse update = productService.update(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(update);
    }
}
