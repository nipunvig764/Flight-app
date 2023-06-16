package com.flightBackend.flight.backend.exceptions;

public class FlightBookingException extends Exception{

    private static final long serialVersionUID = 1L;

    public FlightBookingException(String errorMessage) {
        super(errorMessage);
    }

}
