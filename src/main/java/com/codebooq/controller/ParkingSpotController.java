package com.codebooq.controller;

import com.codebooq.model.domain.request.CreateParkingSpot;
import com.codebooq.model.domain.request.ReserveParkingSpotRequest;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import com.codebooq.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-spot")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @GetMapping("/getAll")
    public List<ParkingSpotResponse> getAllParkingSpots() {
        return parkingSpotService.getAllParkingSpots();
    }

    @PostMapping("/reserve")
    public void reserveParkingSpot(ReserveParkingSpotRequest request) {
        parkingSpotService.reserveParkingSpot(request);
    }

    @PostMapping
    public ParkingSpotResponse createParkingSpot(CreateParkingSpot request) {
        return parkingSpotService.createParkingSpot(request);
    }

    @DeleteMapping("/{id}")
    public void deleteParkingSpot(@PathVariable("id") String id) {
        parkingSpotService.deleteParkingSpot(id);
    }

}
