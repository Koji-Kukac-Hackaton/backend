package com.codebooq.model.domain.request;

import lombok.Builder;

@Builder
public class CreateParkingSpot {

    private Double latitude;
    private Double longitude;
    private String parkingSpotZone;

}
