package com.codebooq.client;

import com.codebooq.model.domain.response.ParkingSpotResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "codebooqClient", url = "${codebooq.api.url}")
public interface CodebooqAPI {

    @GetMapping("/getAll")
    List<ParkingSpotResponse> getAllParkingSpots();



}
