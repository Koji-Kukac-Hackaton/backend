package com.codebooq.model.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReserveParkingSpotRequest {

    private String parkingSpotId;
    private Integer endH;
    private Integer endM;
}
