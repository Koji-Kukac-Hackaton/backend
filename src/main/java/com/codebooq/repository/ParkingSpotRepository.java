package com.codebooq.repository;

import com.codebooq.model.domain.ParkingSpot;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

    @Query(value = "SELECT id, ST_Y(location) as latitude, ST_X(location) as longitude, " +
            "parking_spot_zone, occupied, occupied_timestamp FROM parking_spot WHERE " +
            "6371000 * acos(cos(radians(:latitude)) * cos(radians(ST_Y(location))) * " +
            "cos(radians(ST_X(location)) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(ST_Y(location)))) < :radius",
            nativeQuery = true)
    List<Object[]> findWithinRadius(@Param("latitude") double latitude,
                                    @Param("longitude") double longitude,
                                    @Param("radius") double radius);


}
