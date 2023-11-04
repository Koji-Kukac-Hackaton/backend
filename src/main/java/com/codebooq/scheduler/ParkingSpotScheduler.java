package com.codebooq.scheduler;

import com.codebooq.client.CodebooqAPI;
import com.codebooq.model.domain.ParkingSpot;
import com.codebooq.model.domain.enums.ParkingSpotZone;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import com.codebooq.repository.ParkingSpotRepository;
import com.codebooq.service.ParkingSpotService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingSpotScheduler {

    @Autowired
    private CodebooqAPI codebooqAPI;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Scheduled(fixedRate = 60000)
    public void fetchParkingSpots() {
        List<ParkingSpotResponse> responseList = codebooqAPI.getAllParkingSpots();
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        responseList.forEach(response -> {
            ParkingSpot parkingSpot = new ParkingSpot();
            parkingSpot.setId(response.getId());
            parkingSpot.setParkingSpotZone(ParkingSpotZone.valueOf(response.getParkingSpotZone().toUpperCase()));
            parkingSpot.setOccupiedTimestamp(parkingSpot.getOccupiedTimestamp());
            parkingSpot.setOccupied(response.isOccupied());
            parkingSpot.setLocation(toPoint(response.getLongitude(), response.getLatitude()));
            parkingSpots.add(parkingSpot);
        });

        parkingSpotRepository.saveAll(parkingSpots);
    }

    private Point toPoint(double longitude, double latitude) {
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}
