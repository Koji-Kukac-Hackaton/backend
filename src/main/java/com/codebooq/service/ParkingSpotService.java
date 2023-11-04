package com.codebooq.service;

import com.codebooq.model.domain.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ParkingSpotService {

    @Value("${codebooq.api.bearer-token}")
    private String bearerToken;

    @Value("${codebooq.api.url}")
    private String url;


    public ParkingSpot fetchAllParkingSpots() {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<?> entity = new HttpEntity<>(createHeaders());
        ResponseEntity<ParkingSpot> response = restTemplate.exchange(url + "/ParkingSpot/getAll", HttpMethod.GET, entity, ParkingSpot.class);

        System.out.println(response.getBody());
        return null;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.bearerToken);
        return headers;
    }
}
