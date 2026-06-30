package com.example.practice.kafka.envelope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericEnvelope<T> {
    private String eventType;
    private Instant timestamp;
    private T payload;
}
