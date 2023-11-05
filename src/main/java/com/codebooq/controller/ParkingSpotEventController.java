package com.codebooq.controller;

import com.codebooq.service.ParkingSpotEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
