package com.codebooq.model.domain.request;


import lombok.Builder;

@Builder
public class ReserveParkingSpotRequest {

    private String parkingSpotId;
    private Integer endH;
    private Integer endM;


}
