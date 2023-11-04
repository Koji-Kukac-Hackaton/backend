package com.codebooq.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class ParkingSpotEvent {

    @Id
    @JsonProperty("Id")
    private String id;

    @JsonProperty("IsOccupied")
    private boolean isOccupied;

    @JsonProperty("Time")
    @JsonFormat(pattern = "H:m")
    private LocalTime time;
}
