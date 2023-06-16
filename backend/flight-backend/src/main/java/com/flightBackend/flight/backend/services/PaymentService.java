package com.flightBackend.flight.backend.services;


import com.flightBackend.flight.backend.dto.CreditCardDetailsDTO;
import com.flightBackend.flight.backend.exceptions.CreditCardException;

public interface PaymentService {

    public CreditCardDetailsDTO addCard(CreditCardDetailsDTO creditCardDetialsDTO) throws CreditCardException;
    public String payment(CreditCardDetailsDTO creditCardDetialsDTO) throws CreditCardException;
}
