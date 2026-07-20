package com.example.practice.controller;

import com.example.practice.dto.product.CreateProductRequest;
import com.example.practice.dto.product.ProductProjection;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.dto.product.PutProductRequest;
import com.example.practice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
        return productService.findByIdOrThrow(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse save(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse save = productService.save(request);
        return save;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody PutProductRequest request) {
        return productService.putProduct(id, request);
    }


}
