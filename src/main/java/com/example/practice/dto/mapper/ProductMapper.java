package com.example.practice.dto.mapper;

import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.product.ProductRequest;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.entity.Product;
import com.example.practice.entity.Supplier;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final SupplierRepository supplierRepository;

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getSupplier().getName(),
                product.getName(),
                product.getDescription(),
                product.getPrice().doubleValue());
    }

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        Supplier supplier = supplierRepository.findById(request.supplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        product.setSupplier(supplier);
        product.setPrice(BigDecimal.valueOf(request.price()));
        return product;
    }
}
