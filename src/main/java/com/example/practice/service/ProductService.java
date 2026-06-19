package com.example.practice.service;

import com.example.practice.dao.ProductRepository;
import com.example.practice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElseThrow();
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
