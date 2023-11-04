package com.codebooq.model.domain;

import com.codebooq.model.domain.enums.ParkingSpotZone;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSpot {

    @Id
    private String id;

    private double latitude;
    private double longitude;
    private ParkingSpotZone parkingSpotZone;
    private boolean occupied;
    private LocalDateTime occupiedTimestamp;
}
