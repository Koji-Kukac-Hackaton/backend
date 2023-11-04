package com.codebooq.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParkingSpot {

    @Id
    private String id;

    private String name;


}
