package com.flightBackend.flight.backend.repository;

import com.flightBackend.flight.backend.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightDetails, String> {

    public List<FlightDetails> findBySourceAndDestinationAndFlightAvailableDate(String source, String destination,
                                                                                Date date);
}
