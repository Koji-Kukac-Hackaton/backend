package com.codebooq.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class ParkingSpotAvailability {

    @Id
    private String parkingSpotId;

    private boolean isOccupied;

    private  LocalTime time;
}
