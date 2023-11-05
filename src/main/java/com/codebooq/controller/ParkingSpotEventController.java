package com.codebooq.controller;

import com.codebooq.model.domain.response.ParkingSpotEventResponse;
import com.codebooq.service.ParkingSpotEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-spot-events")
@CrossOrigin(origins = "*")
public class ParkingSpotEventController {

    @Autowired
    ParkingSpotEventService service;

    @GetMapping
    public Object getAllParkingSpotEvents() {
        return service.getAllParkingSpotEvents();
    }

    @GetMapping("/{id}")
    public List<ParkingSpotEventResponse> getParkingSpotEventsBySpotId(@PathVariable("id") String id) {
        return service.getParkingSpotEventsBySpotId(id);
    }
}
