package com.codebooq.model.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotEventResponse {

    private String parkingSpotId;

    @JsonFormat(pattern = "H:m")
    private LocalTime time;

    private boolean isOccupied;

}
