package com.example.practice.service;

import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.mapper.SupplierMapper;
import com.example.practice.dto.supplier.PatchSupplierRequest;
import com.example.practice.dto.supplier.CreateSupplierRequest;
import com.example.practice.entity.Supplier;
import com.example.practice.exceptionHandler.supplier.NoSuchSupplierException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public Supplier getById(Long id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        return byId.orElseThrow(() ->
                new NoSuchSupplierException("Поставщик с таким айди не найден"));
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier save(CreateSupplierRequest request) {
        return supplierRepository.save(supplierMapper.toEntity(request));
    }

    @Transactional
    public Supplier update(Long id, PatchSupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() ->
                new NoSuchSupplierException("Поставщика с таким айди не найдено"));

        if(request.name() != null) {
           supplier.setName(request.name());
        }
        if(request.email() != null) {
            supplier.setEmail(request.email());
        }
        if(request.phone() != null) {
            supplier.setPhone(request.phone());
        }

        return supplier;
    }
}
