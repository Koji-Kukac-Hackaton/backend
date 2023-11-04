package com.codebooq.client;

import com.codebooq.model.domain.request.CreateParkingSpot;
import com.codebooq.model.domain.request.ReserveParkingSpotRequest;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "codebooqClient", url = "${codebooq.api.url}")
public interface CodebooqAPI {

    @GetMapping("/getAll")
    List<ParkingSpotResponse> getAllParkingSpots();

    @PostMapping("/reserve")
    void reserveParkingSpot(ReserveParkingSpotRequest request);

    @PostMapping
    ParkingSpotResponse createParkingSpot(CreateParkingSpot request);

    @DeleteMapping("/{id}")
    void deleteParkingSpot(@PathVariable("id") String id);

}
