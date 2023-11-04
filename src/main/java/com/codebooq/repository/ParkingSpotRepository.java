package com.codebooq.repository;

import com.codebooq.model.domain.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

    // Or Native SQL Query
    @Query(value = "SELECT (SUM(occupied) * 100.0) / COUNT(*) FROM parking_spot WHERE parking_spot_zone=:zone", nativeQuery = true)
    Double findOccupancyPercentageByZoneNative(@Param("zone") String zone);

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
