package com.codebooq.model.domain;

import com.codebooq.model.domain.enums.ParkingSpotZone;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandHistory {

    @Id
    private String id;
    private LocalDateTime timestamp;
    private double occupiedPercentage;
    @Enumerated(EnumType.STRING)
    private ParkingSpotZone parkingSpotZone;
}
