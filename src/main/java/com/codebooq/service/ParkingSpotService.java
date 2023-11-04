package com.codebooq.service;

import com.codebooq.client.CodebooqAPI;
import com.codebooq.model.domain.request.CreateParkingSpot;
import com.codebooq.model.domain.request.ReserveParkingSpotRequest;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ParkingSpotService {

    private final CodebooqAPI codebooqAPI;


    public List<ParkingSpotResponse> getAllParkingSpots() {
        return codebooqAPI.getAllParkingSpots();
    }

    public void reserveParkingSpot(ReserveParkingSpotRequest request) {
        codebooqAPI.reserveParkingSpot(request);
    }

    public ParkingSpotResponse createParkingSpot(CreateParkingSpot request) {
        return codebooqAPI.createParkingSpot(request);
    }

    public void deleteParkingSpot(String id) {
        codebooqAPI.deleteParkingSpot(id);
    }
}
