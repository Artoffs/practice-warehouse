package com.example.practice.exceptionHandler;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String entityName;
    private final Long entityId;

    public ResourceNotFoundException(String entityName, Long id) {
        super(String.format("%s с ID %d не найден", entityName, id));
        this.entityName = entityName;
        this.entityId = id;
    }

    public ResourceNotFoundException(String entityName) {
        super(String.format("%s не найден", entityName));
        this.entityName = entityName;
        this.entityId = null;
    }
}