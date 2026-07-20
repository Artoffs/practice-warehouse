package com.example.practice.dto.mapper;

import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.product.CreateProductRequest;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.entity.Product;
import com.example.practice.entity.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final SupplierRepository supplierRepository;

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getSupplier().getName(),
                product.getName(),
                product.getDescription(),
                product.getPrice().doubleValue());
    }

    public Product toEntity(CreateProductRequest request, Supplier supplier) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setSupplier(supplier);
        product.setPrice(request.price());
        return product;
    }
}
