package com.codebooq.service;

import com.codebooq.model.domain.enums.ParkingSpotZone;
import com.codebooq.repository.DemandHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PriceService {


    @Autowired
    private OccupancyService occupancyService;

    @Value("${zones.base-prices.zone1}")
    private double zone1Price;

    @Value("${zones.base-prices.zone2}")
    private double zone2Price;

    @Value("${zones.base-prices.zone3}")
    private double zone3Price;

    @Value("${zones.base-prices.zone4}")
    private double zone4Price;

    public Map<String, Double> calculatePrices() {
        int hour = LocalDateTime.now().getMinute();
        Map<String, Double> prices = new HashMap<>();
        for (ParkingSpotZone zone : ParkingSpotZone.values()) {
            double averageOccupancy = occupancyService.findAverageByHourAndZone(hour, zone.toString());
            double currentOccupancy = occupancyService.findOccupancyByZone(zone.toString());
            System.out.println(averageOccupancy);
            System.out.println(currentOccupancy);
            double zonePrice = 0;
            switch (zone) {
                case ZONE1 -> zonePrice = zone1Price;
                case ZONE2 -> zonePrice = zone2Price;
                case ZONE3 -> zonePrice = zone3Price;
                case ZONE4 -> zonePrice = zone4Price;
            }
            double endPrice = zonePrice * (1 + (currentOccupancy - averageOccupancy) / 100);
            String formattedValue = String.format("%.2f", endPrice);
            double roundedValue = Double.parseDouble(formattedValue);
            prices.put(zone.name(), roundedValue);
        }
        return prices;
    }

}
