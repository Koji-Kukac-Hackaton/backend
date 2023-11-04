package com.codebooq.scheduler;

import com.codebooq.model.domain.DemandHistory;
import com.codebooq.model.domain.enums.ParkingSpotZone;
import com.codebooq.repository.DemandHistoryRepository;
import com.codebooq.repository.ParkingSpotRepository;
import com.codebooq.service.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DemandHistoryScheduler {

    @Autowired
    private ParkingSpotRepository repository;

    @Autowired
    private DemandHistoryRepository demandHistoryRepository;
    @Autowired
    private OccupancyService occupancyService;


    @Scheduled(cron = "0 * * * * *")
    public void executeTaskConditional() {
        LocalDateTime now = LocalDateTime.now();
        int minute = now.getMinute();

        if (!((minute >= 24 && minute < 30) || (minute >= 54))) {
            var occupancy1 = occupancyService.findOccupancyByZone("ZONE1");
            repository.flush();
            var occupancy2 = occupancyService.findOccupancyByZone("ZONE2");
            repository.flush();
            var occupancy3 = occupancyService.findOccupancyByZone("ZONE3");
            repository.flush();
            var occupancy4 = occupancyService.findOccupancyByZone("ZONE4");
            repository.flush();
            for (ParkingSpotZone zone : ParkingSpotZone.values()) {
                double occupancy = 0;
                switch (zone) {
                    case ZONE1 -> occupancy = occupancy1;
                    case ZONE2 -> occupancy = occupancy2;
                    case ZONE3 -> occupancy = occupancy3;
                    case ZONE4 -> occupancy = occupancy4;
                }
                DemandHistory history = DemandHistory.builder()
                        .id(UUID.randomUUID().toString())
                        .parkingSpotZone(zone)
                        .timestamp(LocalDateTime.now().withSecond(0).withNano(0))
                        .occupiedPercentage(occupancy)
                        .build();
                demandHistoryRepository.save(history);
            }
        }
    }

    @Transactional
    public Double readData(String zone) {
        return occupancyService.findOccupancyByZone(zone);
    }
}
