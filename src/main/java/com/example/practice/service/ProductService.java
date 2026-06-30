package com.example.practice.service;

import com.example.practice.dao.ProductRepository;
import com.example.practice.dto.mapper.ProductMapper;
import com.example.practice.dto.product.CreateProductRequest;
import com.example.practice.dto.product.ProductProjection;
import com.example.practice.dto.product.ProductResponse;
import com.example.practice.dto.product.PutProductRequest;
import com.example.practice.entity.Product;
import com.example.practice.entity.Supplier;
import com.example.practice.exceptionHandler.InvalidReferenceException;
import com.example.practice.exceptionHandler.ResourceNotFoundException;
import com.example.practice.kafka.KafkaEventProducer;
import com.example.practice.kafka.KafkaTopics;
import com.example.practice.kafka.event.ProductCreatedEvent;
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
    private final SupplierService supplierService;
    private final ProductMapper productMapper;
    private final KafkaEventProducer producer;


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductResponse findByIdOrThrow(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.map(productMapper::toResponse).orElseThrow(() ->
                new ResourceNotFoundException("Продукт", id));
    }


    public Page<ProductProjection> getAll(Pageable pageable) {
        return productRepository.findAllProjections(pageable);
    }

    public ProductResponse save(CreateProductRequest request) {
        Supplier supplier = supplierService.findById((request.supplierId()))
                .orElseThrow(() ->
                        new InvalidReferenceException("поставщик", request.supplierId()));

        Product entity = productMapper.toEntity(request, supplier);

        Product save = productRepository.save(entity);

        ProductCreatedEvent productCreatedEvent =
                new ProductCreatedEvent(save.getId(), save.getName(), save.getPrice());

        producer.send(KafkaTopics.TEST_TOPIC, productCreatedEvent, "PRODUCT_CREATED");

        return productMapper.toResponse(save);
    }

    @Transactional
    public ProductResponse putProduct(Long id, PutProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Продукт", id));


        if (request.supplierId() != null) {
            Supplier supplier = supplierService.findById(request.supplierId())
                    .orElseThrow(() ->
                            new InvalidReferenceException("поставщик", request.supplierId()));

            product.setSupplier(supplier);
        }

        if (request.name() != null) {
            product.setName(request.name());
        }

        if (request.description() != null) {
            product.setDescription(request.description());
        }

        if (request.price() != null) {
            product.setPrice(request.price());
        }

        return productMapper.toResponse(product);
    }

}
