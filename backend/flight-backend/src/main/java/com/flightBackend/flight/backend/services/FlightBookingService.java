package com.flightBackend.flight.backend.services;


import com.flightBackend.flight.backend.dto.PassengerDetailsDTO;
import com.flightBackend.flight.backend.dto.TicketDetailsDTO;
import com.flightBackend.flight.backend.exceptions.FlightBookingException;

import java.util.List;

public interface FlightBookingService {

    public TicketDetailsDTO bookFlight(String flightId, String email, List<PassengerDetailsDTO> passengerDetailsDTOs) throws FlightBookingException;
}
