package com.codebooq.model.domain.response;

import com.codebooq.model.domain.enums.ParkingSpotZone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DemandHistoryResponse {
    private String id;
    private LocalDateTime timestamp;
    private double occupiedPercentage;
    private String parkingSpotZone;
}
