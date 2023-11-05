package com.codebooq.scheduler;

import com.codebooq.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PriceSenderScheduler {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private PriceService priceService;

    @Scheduled(cron = "30 * * * * *")
    public void sendPrices() {

        Map<String, Double> map = priceService.calculatePrices();
        template.convertAndSend("/topic/parkingSpotEvents", map);
    }
}
