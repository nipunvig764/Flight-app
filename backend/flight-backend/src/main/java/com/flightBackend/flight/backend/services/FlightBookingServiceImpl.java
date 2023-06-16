package com.flightBackend.flight.backend.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flightBackend.flight.backend.dto.PassengerDetailsDTO;
import com.flightBackend.flight.backend.dto.TicketDetailsDTO;
import com.flightBackend.flight.backend.entity.FlightDetails;
import com.flightBackend.flight.backend.entity.PassengerDetails;
import com.flightBackend.flight.backend.entity.TicketDetails;
import com.flightBackend.flight.backend.entity.UserDetail;
import com.flightBackend.flight.backend.exceptions.FlightBookingException;
import com.flightBackend.flight.backend.exceptions.InfyGoConstants;
import com.flightBackend.flight.backend.repository.FlightRepository;
import com.flightBackend.flight.backend.repository.PassengerRepository;
import com.flightBackend.flight.backend.repository.TicketRepository;
import com.flightBackend.flight.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service(value = "flightBookingService")
public class FlightBookingServiceImpl implements FlightBookingService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapper modelMapper;

    private static Logger log = LoggerFactory.getLogger(FlightBookingServiceImpl.class);

    @Override
    public TicketDetailsDTO bookFlight(String flightId, String email, List<PassengerDetailsDTO> passengerDetailsDTOs)
            throws FlightBookingException {
        Optional<FlightDetails> opFlightDetails = flightRepository.findById(flightId);
        FlightDetails flightDetails = opFlightDetails.orElseThrow(
                () -> new FlightBookingException(environment.getProperty(InfyGoConstants.NO_FLIGHT_FOUND.toString())));
        Optional<UserDetail> opUser = userRepository.findById(email);
        UserDetail user = opUser.orElseThrow(
                () -> new FlightBookingException(environment.getProperty(InfyGoConstants.USER_NOT_FOUND.toString())));
        int seats = passengerDetailsDTOs.size();
        TicketDetails savedTicket = null;
        if (flightDetails.getSeatCount() < seats) {
            throw new FlightBookingException(environment.getProperty(InfyGoConstants.SEATS_NOT_AVAILABLE.toString()));
        } else {
            flightDetails.setSeatCount(flightDetails.getSeatCount() - seats);
            flightRepository.saveAndFlush(flightDetails);

            Date date = new Date(System.currentTimeMillis());

            TicketDetails ticketDetails = new TicketDetails();
            ticketDetails.setBookingDate(date);
            ticketDetails.setDepartureDate(flightDetails.getFlightAvailableDate());
            ticketDetails.setDepartureTime(flightDetails.getDepartureTime());
            ticketDetails.setFlightId(flightDetails);
            ticketDetails.setNoOfSeats(seats);
            ticketDetails.setTotalFare(seats * flightDetails.getFare());
            ticketDetails.setUserDetails(user);
            savedTicket = ticketRepository.saveAndFlush(ticketDetails);

            List<PassengerDetails> passengerList = new ArrayList<>();
            for (PassengerDetailsDTO passengerDetailsDTO : passengerDetailsDTOs) {
                PassengerDetails passenger = new PassengerDetails();
                passenger.setPassengerAge(passengerDetailsDTO.getPassengerAge());
                passenger.setPassengerGender(passengerDetailsDTO.getPassengerGender());
                passenger.setPassengerName(passengerDetailsDTO.getPassengerName());
                passenger.setEmailAcknowledgement(false);
                passenger.setTicketDetails(savedTicket);
                passengerList.add(passenger);
            }

            List<PassengerDetails> savedPassengers = passengerRepository.saveAllAndFlush(passengerList);
            for (PassengerDetails savedPassenger : savedPassengers)
                log.info("Saved passenger - {}", savedPassenger.getPassengerName());

            ticketDetails.setPassengerDetails(savedPassengers);
            ticketRepository.save(ticketDetails);
        }
        return modelMapper.map(savedTicket, TicketDetailsDTO.class);
    }

}
