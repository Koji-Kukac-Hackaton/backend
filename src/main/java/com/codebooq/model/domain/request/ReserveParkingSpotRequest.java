package com.codebooq.model.domain.request;


import lombok.Getter;

@Getter
public class ReserveParkingSpotRequest {

    private String parkingSpotId;
    private Integer endH;
    private Integer endM;


}
