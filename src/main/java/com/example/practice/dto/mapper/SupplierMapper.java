package com.example.practice.dto.mapper;

import com.example.practice.dto.supplier.SupplierRequest;
import com.example.practice.entity.Supplier;
import org.springframework.stereotype.Service;

@Service
public class SupplierMapper {

    public Supplier toEntity(SupplierRequest request) {

        Supplier supplier = new Supplier();

        supplier.setName(request.name());
        supplier.setEmail(request.email());
        supplier.setPhone(request.phone());

        return supplier;
    }
}
