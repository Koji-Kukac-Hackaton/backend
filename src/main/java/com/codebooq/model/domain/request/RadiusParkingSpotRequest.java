package com.codebooq.model.domain.request;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class RadiusParkingSpotRequest {

    private double latitude;
    private double longitude;
    private double radius;
}
