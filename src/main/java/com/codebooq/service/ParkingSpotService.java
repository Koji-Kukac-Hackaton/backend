package com.codebooq.service;

import com.codebooq.client.CodebooqAPI;
import com.codebooq.model.domain.ParkingSpot;
import com.codebooq.model.domain.Reservation;
import com.codebooq.model.domain.User;
import com.codebooq.model.domain.enums.ParkingSpotZone;
import com.codebooq.model.domain.request.CreateParkingSpot;
import com.codebooq.model.domain.request.ReserveParkingSpotRequest;
import com.codebooq.model.domain.response.ParkingSpotResponse;
import com.codebooq.repository.ParkingSpotRepository;
import com.codebooq.repository.ReservationRepository;
import com.codebooq.repository.UserRepository;
import com.codebooq.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ParkingSpotService {

    private final CodebooqAPI codebooqAPI;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ParkingSpotRepository parkingSpotRepository;


    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll();
    }

    public void reserveParkingSpot(ReserveParkingSpotRequest request) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        ParkingSpot parkingSpot = parkingSpotRepository.findById(request.getParkingSpotId())
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));

        Reservation reservation = Reservation.builder()
                .user(user)
                .parkingSpot(parkingSpot)
                .build();

        reservationRepository.save(reservation);
        codebooqAPI.reserveParkingSpot(request);

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
}
