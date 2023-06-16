package com.flightBackend.flight.backend.entity;


public class WrongDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WrongDateException(String errorMessage) {
        super(errorMessage);
    }
}
