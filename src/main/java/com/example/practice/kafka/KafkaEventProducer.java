package com.example.practice.kafka;

import com.example.practice.kafka.envelope.GenericEnvelope;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void send(KafkaTopics topic, String key, T event, String eventType) {

        GenericEnvelope<T> envelope = new GenericEnvelope<>(eventType, Instant.now(), event);

        kafkaTemplate.send(topic.getTopicName(), key, envelope);
    }

    public <T> void send(KafkaTopics topic, T event, String eventType) {

        GenericEnvelope<T> envelope = new GenericEnvelope<>(eventType, Instant.now(), event);

        kafkaTemplate.send(topic.getTopicName(), envelope);
    }
}
