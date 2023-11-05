package com.codebooq.service;

import com.codebooq.model.domain.ParkingSpotEvent;
import com.codebooq.model.domain.response.ParkingSpotEventResponse;
import com.codebooq.repository.ParkingSpotEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingSpotEventService {

    @Autowired
    private ParkingSpotEventRepository parkingSpotEventRepository;

    public List<ParkingSpotEventResponse> getAllParkingSpotEvents() {

        List<ParkingSpotEvent> events = parkingSpotEventRepository.findAll();

        return events.stream()
                .map(event -> {
                    ParkingSpotEventResponse response = new ParkingSpotEventResponse();
                    response.setParkingSpotId(event.getParkingSpotId());
                    response.setTime(event.getTime());
                    response.setOccupied(event.isOccupied());
                    return response;
                }).toList();
    }
}
