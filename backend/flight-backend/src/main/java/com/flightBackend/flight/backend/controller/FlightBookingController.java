package com.flightBackend.flight.backend.controller;

import com.flightBackend.flight.backend.dto.PassengerDetailsDTO;
import com.flightBackend.flight.backend.dto.TicketDetailsDTO;
import com.flightBackend.flight.backend.exceptions.FlightBookingException;
import com.flightBackend.flight.backend.services.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/book")
public class FlightBookingController {

    @Autowired
    private FlightBookingService flightBookingService;

    @PostMapping("/{flightId}/{userEmail}")
    public ResponseEntity<TicketDetailsDTO> bookFlight(@PathVariable String flightId, @PathVariable String userEmail,
                                                       @RequestBody List<PassengerDetailsDTO> passengerDetailsDTOs) throws FlightBookingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(flightBookingService.bookFlight(flightId, userEmail, passengerDetailsDTOs));
    }
}
