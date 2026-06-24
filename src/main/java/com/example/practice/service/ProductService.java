package com.example.practice.service;

import com.example.practice.dao.ProductRepository;
import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.mapper.ProductMapper;
import com.example.practice.dto.product.ProductProjection;
import com.example.practice.dto.product.ProductRequest;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.entity.Product;
import com.example.practice.exceptionHandler.product.NoSuchProductException;
import com.example.practice.exceptionHandler.supplier.NoSuchSupplierException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    public ProductResponse getById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.map(productMapper::toResponse).orElseThrow(() ->
                new NoSuchProductException("Продукт с указанным айди не найден"));
    }


    public Page<ProductProjection> getAll(Pageable pageable) {
        return productRepository.findAllProjections(pageable);
    }

    public Product save(ProductRequest request) {
        supplierRepository.findById(
                request.supplierId())
                .orElseThrow(() -> new NoSuchSupplierException(
                        "Поставщик с указанным айди не найден"
                ));

        return productRepository.save(productMapper.toEntity(request));
    }
}
