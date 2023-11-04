package com.codebooq.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotScheduler {

    @Scheduled(fixedRate = 60000)
    public void fetchParkingSpots() {
        System.out.println("Running fixed rate task every minute!");
    }
}
