package com.codebooq.service;

import com.codebooq.model.domain.DemandHistory;
import com.codebooq.model.domain.ParkingSpot;
import com.codebooq.repository.DemandHistoryRepository;
import com.codebooq.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class OccupancyService {

    @Autowired
    private ParkingSpotRepository repository;

    @Autowired
    private DemandHistoryRepository demandHistoryRepository;

    public Double findOccupancyByZone(String zone) {
        List<ParkingSpot> parkingSpots = repository.findAll();
        long totalSpotsInZone = parkingSpots.stream()
                .filter(spot -> spot.getParkingSpotZone().toString().equals(zone))
                .count();

        long occupiedSpotsInZone = parkingSpots.stream()
                .filter(spot -> spot.getParkingSpotZone().toString().equals(zone) && spot.isOccupied())
                .count();

        return totalSpotsInZone > 0 ? (double) occupiedSpotsInZone / totalSpotsInZone * 100 : 0;
    }

    public Double findAverageByHourAndZone(int hour, String zone) {
        List<DemandHistory> histories = demandHistoryRepository.findAll();

        OptionalDouble occupancy = histories.stream()
                .filter(history -> history.getTimestamp().getMinute() == hour
                        && history.getParkingSpotZone().toString().equals(zone))
                .mapToDouble(DemandHistory::getOccupiedPercentage)
                .average();

        return occupancy.getAsDouble();
    }

}
