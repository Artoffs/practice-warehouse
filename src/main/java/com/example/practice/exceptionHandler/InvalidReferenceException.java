package com.example.practice.exceptionHandler;

import lombok.Getter;

@Getter
public class InvalidReferenceException extends RuntimeException {

    private String entityName;
    private Long entityId;

    public InvalidReferenceException(String entityName, Long id) {
        super(String.format("Ссылка на %s с ID %d не существует", entityName, id));
        this.entityName = entityName;
        this.entityId = id;
    }

    public InvalidReferenceException(String message) {
        super(message);
    }
}
