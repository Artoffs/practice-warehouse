package com.example.practice.kafka;

public enum KafkaTopics {
    TEST_TOPIC("test-topic");

    private final String topicName;

    KafkaTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
