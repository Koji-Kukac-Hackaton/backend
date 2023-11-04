package com.codebooq.service;

import com.codebooq.model.domain.ParkingSpotEvent;
import com.codebooq.repository.ParkingSpotEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAsync
public class KafkaConsumerService {

    @Autowired
    private ParkingSpotEventRepository repository;

    @KafkaListener(topics = "team6", groupId = "UvenuliTulipani-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<String> messages) throws JsonProcessingException {
        System.out.println(messages.size());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<ParkingSpotEvent> events = mapper.readValue(messages.toString(), new TypeReference<>(){});
        System.out.println(events.get(0));
        repository.saveAll(events);
    }
}