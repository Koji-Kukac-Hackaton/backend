package com.codebooq.model.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateParkingSpot {

    private Double latitude;
    private Double longitude;
    private String parkingSpotZone;
}
