package com.codebooq.model.domain.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingSpotResponse {

    private String id;

    private double latitude;
    private double longitude;
    private String parkingSpotZone;
    private boolean occupied;
    private LocalDateTime occupiedTimestamp;
}