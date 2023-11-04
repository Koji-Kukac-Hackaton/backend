package com.codebooq.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "team6", groupId = "UvenuliTulipani-consumer-group")
    public void listen(String message) {
        System.out.println("Received message in group UvenuliTulipani-consumer-group: " + message);
    }
}
