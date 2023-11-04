package com.codebooq.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotEvent {

    @Id
    private String id;

    @JsonProperty("Id")
    private String parkingSpotId;

    @JsonProperty("IsOccupied")
    private boolean isOccupied;

    @JsonProperty("Time")
    @JsonFormat(pattern = "H:m")
    private LocalTime time;
}
