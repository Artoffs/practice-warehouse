package com.example.practice.controller;

import com.example.practice.entity.Supplier;
import com.example.practice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers/{id}")
    public Supplier supplier(@PathVariable Long id) {
        return supplierService.getById(id);
    }


    @GetMapping("/suppliers")
    public List<Supplier> suppliers() {
        return supplierService.getAll();
    }
}
