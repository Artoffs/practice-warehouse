package com.example.practice.service;

import com.example.practice.dao.SupplierRepository;
import com.example.practice.dto.mapper.SupplierMapper;
import com.example.practice.dto.supplier.CreateSupplierRequest;
import com.example.practice.dto.supplier.PatchSupplierRequest;
import com.example.practice.entity.Supplier;
import com.example.practice.exceptionHandler.ResourceNotFoundException;
import com.example.practice.kafka.KafkaEventProducer;
import com.example.practice.kafka.KafkaTopics;
import com.example.practice.kafka.event.SupplierCreatedEvent;
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
    private final KafkaEventProducer producer;

    // Для внутреннего использования
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    // Для гет метода
    public Supplier findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Поставщик", id));
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier save(CreateSupplierRequest request) {
        Supplier save = supplierRepository.save(supplierMapper.toEntity(request));

        producer.send(
                KafkaTopics.TEST_TOPIC,
                new SupplierCreatedEvent(save.getId(), save.getName()),
                "supplier-created"
        );

        return save;
    }

    @Transactional
    public Supplier update(Long id, PatchSupplierRequest request) {
        Supplier supplier = findByIdOrThrow(id);

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
