package com.example.practice.service;

import com.example.practice.dao.ProductRepository;
import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.mapper.ProductMapper;
import com.example.practice.dto.product.PatchProductRequest;
import com.example.practice.dto.product.ProductProjection;
import com.example.practice.dto.product.CreateProductRequest;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.entity.Product;
import com.example.practice.entity.Supplier;
import com.example.practice.exceptionHandler.product.NoSuchProductException;
import com.example.practice.exceptionHandler.supplier.NoSuchSupplierException;
import jakarta.transaction.Transactional;
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

    public ProductResponse save(CreateProductRequest request) {
        supplierRepository.findById(
                request.supplierId())
                .orElseThrow(() -> new NoSuchSupplierException(
                        "Поставщик с указанным айди не найден"
                ));

        Product save = productRepository.save(productMapper.toEntity(request));
        return productMapper.toResponse(save);
    }

    @Transactional
    public ProductResponse update(Long id, PatchProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NoSuchProductException("Продукт с указанным айди не найден"));

        if (request.supplierId().isPresent()) {
            Long supplierId = request.supplierId().get();
            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() ->
                            new NoSuchSupplierException("Поставщик с указанным айди не найден"));
            product.setSupplier(supplier);
        }

        request.name().ifPresent(product::setName);
        request.description().ifPresent(product::setDescription);
        request.price().ifPresent(product::setPrice);

        return productMapper.toResponse(product);
    }
}
