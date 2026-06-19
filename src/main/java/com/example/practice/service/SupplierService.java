package com.example.practice.service;

import com.example.practice.dao.SupplierRepository;
import com.example.practice.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier getById(Long id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        return byId.orElseThrow();
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }
}
