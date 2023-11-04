package com.codebooq.repository;

import com.codebooq.model.domain.ParkingSpotEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotEventRepository extends JpaRepository<ParkingSpotEvent, String> {
}
