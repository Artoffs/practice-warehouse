package com.example.practice.controller;

import com.example.practice.dto.supplier.PatchSupplierRequest;
import com.example.practice.dto.supplier.CreateSupplierRequest;
import com.example.practice.entity.Supplier;
import com.example.practice.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }


    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @PostMapping
    public Supplier save(@RequestBody @Valid CreateSupplierRequest request) {
        return supplierService.save(request);
    }

    @PatchMapping("/{id}")
    public Supplier update(@PathVariable Long id, @RequestBody PatchSupplierRequest request) {
        return supplierService.update(id, request);
    }


}