package com.example.practice.kafka;

import com.example.practice.kafka.envelope.GenericEnvelope;
import com.example.practice.kafka.event.SupplierCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "test-topic")
    public void consumeMessage(GenericEnvelope<SupplierCreatedEvent> message) {
        System.out.println("Полученное сообщение из Кафки: " + message);
    }
}
