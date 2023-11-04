package com.codebooq.service;

import com.codebooq.model.domain.ParkingSpotEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void notifyFrontend(List<ParkingSpotEvent> events) {
        messagingTemplate.convertAndSend("/topic/parkingSpotEvents", events);
    }
}
