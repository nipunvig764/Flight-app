package com.flightBackend.flight.backend.services;

import com.flightBackend.flight.backend.dto.FlightDetailsDTO;
import com.flightBackend.flight.backend.entity.FlightDetails;

import java.text.ParseException;
import java.util.List;


public interface FlightService {
    public String addFlight(FlightDetailsDTO flightDetailsDTO);

    public List<String> getFlightSourceCities();

    public List<String> getFlightDestinationCities();

    public List<FlightDetails> getFlights(String source, String destination, String date) throws ParseException;

    public FlightDetails getFlightDetails(String flightId);
}

