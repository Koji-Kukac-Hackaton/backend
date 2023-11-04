package com.codebooq.service;

import com.codebooq.model.domain.ParkingSpotEvent;
import com.codebooq.repository.ParkingSpotEventRepository;
import com.codebooq.repository.ParkingSpotRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@EnableAsync
public class KafkaConsumerService {

    @Autowired
    private ParkingSpotEventRepository parkingSpotEventRepository;
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @KafkaListener(topics = "team6", groupId = "UvenuliTulipani-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<String> messages) throws JsonProcessingException {
        System.out.println(messages.size());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<ParkingSpotEvent> events = mapper.readValue(messages.toString(), new TypeReference<>(){});
        events.forEach(e -> e.setId(UUID.randomUUID().toString()));
        parkingSpotEventRepository.saveAll(events);
        events.forEach(event -> parkingSpotRepository.findById(event.getParkingSpotId()).ifPresent(parkingSpot -> {
            parkingSpot.setOccupied(event.isOccupied());
            if (event.isOccupied()) {
                parkingSpot.setOccupiedTimestamp(LocalDateTime.from(event.getTime()));
            } else {
                parkingSpot.setOccupiedTimestamp(null);
            }
            parkingSpotRepository.save(parkingSpot);
        }));
    }
}