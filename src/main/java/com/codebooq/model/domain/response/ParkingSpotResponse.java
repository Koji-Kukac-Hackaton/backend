package com.codebooq.model.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotResponse {

    private String id;

    private double latitude;
    private double longitude;
    private String parkingSpotZone;
    private boolean occupied;
    private LocalDateTime occupiedTimestamp;
}