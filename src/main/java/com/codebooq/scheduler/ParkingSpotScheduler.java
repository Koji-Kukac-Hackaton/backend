package com.codebooq.scheduler;

import com.codebooq.client.CodebooqAPI;
import com.codebooq.model.domain.ParkingSpot;
import com.codebooq.model.domain.enums.ParkingSpotZone;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import com.codebooq.repository.ParkingSpotRepository;
import com.codebooq.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ParkingSpotScheduler {

    @Autowired
    private CodebooqAPI codebooqAPI;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Scheduled(cron = "5 * * * * *")
    public void fetchAndUpdateParkingSpots() {
        List<ParkingSpotResponse> responseList = codebooqAPI.getAllParkingSpots();
        List<ParkingSpot> existingParkingSpots = parkingSpotRepository.findAll();
        Map<String, ParkingSpot> existingParkingSpotMap = existingParkingSpots.stream()
                .collect(Collectors.toMap(ParkingSpot::getId, Function.identity()));

        List<ParkingSpot> spotsToUpdate = new ArrayList<>();

        for (ParkingSpotResponse response : responseList) {
            ParkingSpot existingSpot = existingParkingSpotMap.get(response.getId());
            if (existingSpot == null || hasChanged(existingSpot, response)) {
                ParkingSpot parkingSpot = convertResponseToParkingSpot(response);
                spotsToUpdate.add(parkingSpot);
            }
        }

        if (!spotsToUpdate.isEmpty()) {
            parkingSpotRepository.saveAll(spotsToUpdate);
        }
    }

    private boolean hasChanged(ParkingSpot existingSpot, ParkingSpotResponse response) {
        return existingSpot.isOccupied() != response.isOccupied() ||
                !existingSpot.getParkingSpotZone().equals(ParkingSpotZone.valueOf(response.getParkingSpotZone().toUpperCase()))
                || !response.getOccupiedTimestamp().equals(existingSpot.getOccupiedTimestamp());
    }

    private ParkingSpot convertResponseToParkingSpot(ParkingSpotResponse response) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setId(response.getId());
        parkingSpot.setParkingSpotZone(ParkingSpotZone.valueOf(response.getParkingSpotZone().toUpperCase()));
        parkingSpot.setOccupiedTimestamp(parkingSpot.getOccupiedTimestamp());
        parkingSpot.setOccupied(response.isOccupied());
        parkingSpot.setLocation(Util.toPoint(response.getLongitude(), response.getLatitude()));
        return parkingSpot;
    }

}
