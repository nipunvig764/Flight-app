package com.flightBackend.flight.backend.exceptions;

public class CreditCardException extends Exception{

    private static final long serialVersionUID = 1L;

    public CreditCardException(String errorMessage) {
        super(errorMessage);
    }

}


