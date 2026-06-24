package com.example.practice.service;

import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.mapper.SupplierMapper;
import com.example.practice.dto.supplier.SupplierRequest;
import com.example.practice.entity.Supplier;
import com.example.practice.exceptionHandler.supplier.NoSuchSupplierException;
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

    public Supplier save(SupplierRequest request) {
        return supplierRepository.save(supplierMapper.toEntity(request));
    }
}
