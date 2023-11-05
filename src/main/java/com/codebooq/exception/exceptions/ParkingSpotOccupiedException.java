package com.codebooq.exception.exceptions;

public class ParkingSpotOccupiedException extends RuntimeException {

        public ParkingSpotOccupiedException() {
            super("Parking spot already occupied!");
        }

}
