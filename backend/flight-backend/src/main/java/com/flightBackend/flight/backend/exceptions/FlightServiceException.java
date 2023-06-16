package com.flightBackend.flight.backend.exceptions;


public class FlightServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FlightServiceException(String errorMessage) {
        super(errorMessage);
    }
}
