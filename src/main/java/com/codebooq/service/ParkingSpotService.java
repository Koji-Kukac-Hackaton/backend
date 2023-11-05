package com.codebooq.service;

import com.codebooq.client.CodebooqAPI;
import com.codebooq.exception.exceptions.ParkingSpotOccupiedException;
import com.codebooq.model.domain.ParkingSpot;
import com.codebooq.model.domain.Reservation;
import com.codebooq.model.domain.User;
import com.codebooq.model.domain.enums.ParkingSpotZone;
import com.codebooq.model.domain.request.CreateParkingSpot;
import com.codebooq.model.domain.request.RadiusParkingSpotRequest;
import com.codebooq.model.domain.request.ReserveParkingSpotRequest;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import com.codebooq.repository.ParkingSpotRepository;
import com.codebooq.repository.ReservationRepository;
import com.codebooq.repository.UserRepository;
import com.codebooq.util.Util;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ParkingSpotService {

    private final CodebooqAPI codebooqAPI;
    private final ReservationRepository reservationRepository;
    private final ParkingSpotRepository parkingSpotRepository;


    public List<ParkingSpotResponse> getAllParkingSpots() {

        return parkingSpotRepository.findAll().stream().map(parkingSpot -> ParkingSpotResponse.builder()
                .id(parkingSpot.getId())
                .latitude(parkingSpot.getLocation().getY())
                .longitude(parkingSpot.getLocation().getX())
                .occupied(parkingSpot.isOccupied())
                .occupiedTimestamp(parkingSpot.getOccupiedTimestamp())
                .parkingSpotZone(parkingSpot.getParkingSpotZone().name())
                .build()).toList();
    }

    public void reserveParkingSpot(ReserveParkingSpotRequest request) {

        ParkingSpot parkingSpot = parkingSpotRepository.findById(request.getParkingSpotId())
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));

        if (parkingSpot.isOccupied()) {
            throw new ParkingSpotOccupiedException();
        }

        parkingSpot.setOccupiedTimestamp(LocalDateTime.now());
        parkingSpot.setOccupied(true);

        Reservation reservation = Reservation.builder()
                .parkingSpot(parkingSpot)
                .build();

        reservationRepository.save(reservation);
        parkingSpotRepository.save(parkingSpot);
        codebooqAPI.reserveParkingSpot(request);
    }

    public ParkingSpotResponse createParkingSpot(CreateParkingSpot request) {
        ParkingSpot parkingSpot = ParkingSpot.builder()
                .parkingSpotZone(ParkingSpotZone.valueOf(request.getParkingSpotZone().toUpperCase()))
                .location(Util.toPoint(request.getLongitude(), request.getLatitude()))
                .id(UUID.randomUUID().toString())
                .occupiedTimestamp(null)
                .occupied(false)
                .build();
        parkingSpotRepository.save(parkingSpot);
        return codebooqAPI.createParkingSpot(request);
    }

    public void deleteParkingSpot(String id) {
        codebooqAPI.deleteParkingSpot(id);
    }

    public List<ParkingSpotResponse> getParkingSpotsInRadius(RadiusParkingSpotRequest request) {
        List<Object[]> result = parkingSpotRepository.findWithinRadius(request.getLatitude(), request.getLongitude(), request.getRadius());
        List<ParkingSpotResponse> responses = new ArrayList<>();

        for(Object[] row : result){
            String id = (String) row[0];
            double lat = (Double) row[1];
            double lon = (Double) row[2];
            Byte zone = (Byte) row[3];
            boolean occupied = (Boolean) row[4];
            LocalDateTime timestamp = row[5] != null ? ((Timestamp) row[5]).toLocalDateTime() : null;

            ParkingSpotResponse response = ParkingSpotResponse.builder()
                    .id(id)
                    .latitude(lat)
                    .longitude(lon)
                    .parkingSpotZone("ZONE"+zone)
                    .occupied(occupied)
                    .occupiedTimestamp(timestamp)
                    .build();

            responses.add(response);
        }
        return responses;
    }
}
