package com.codebooq.service;

import com.codebooq.model.domain.ParkingSpotEvent;
import com.codebooq.repository.ParkingSpotEventRepository;
import com.codebooq.repository.ParkingSpotRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
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
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private SimpMessagingTemplate template;

    @Async
    @KafkaListener(topics = "team6", groupId = "UvenuliTulipani-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<String> messages) throws JsonProcessingException {
        processMessagesAsync(messages);
    };

    @Async
    public void processMessagesAsync(List<String> messages) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new JavaTimeModule());
                    List<ParkingSpotEvent> events = mapper.readValue(messages.toString(), new TypeReference<>() {});
                    events.forEach(e -> e.setId(UUID.randomUUID().toString()));
                    parkingSpotEventRepository.saveAll(events);
                    processParkingSpots(events);
                    template.convertAndSend("/topic/parkingSpotEvents", events);


                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error processing messages", e);
                }
            }
        });
    }

    private void processParkingSpots(List<ParkingSpotEvent> events) {
        events.forEach(event -> parkingSpotRepository.findById(event.getParkingSpotId()).ifPresent(parkingSpot -> {
            parkingSpot.setOccupied(event.isOccupied());
            if (event.isOccupied()) {
                parkingSpot.setOccupiedTimestamp(LocalDateTime.of(LocalDate.now(), event.getTime()));
            } else {
                parkingSpot.setOccupiedTimestamp(null);
            }
            parkingSpotRepository.save(parkingSpot);
        }));
    }

}