package com.codebooq.model.domain;

import com.codebooq.model.domain.enums.ParkingSpotZone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Point;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParkingSpot {

    @Id
    private String id;
    private Point location;
    private ParkingSpotZone parkingSpotZone;
    private boolean occupied;
    private LocalDateTime occupiedTimestamp;
}
