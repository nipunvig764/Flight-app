package com.flightBackend.flight.backend.services;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.flightBackend.flight.backend.dto.FlightDetailsDTO;
import com.flightBackend.flight.backend.entity.FlightDetails;
import com.flightBackend.flight.backend.exceptions.FlightServiceException;
import com.flightBackend.flight.backend.exceptions.InfyGoConstants;
import com.flightBackend.flight.backend.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service(value = "flightService")
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Environment environment;

    @Override
    public String addFlight(FlightDetailsDTO flightDetailsDTO) {
        Optional<FlightDetails> optionalFlight = flightRepository.findById(flightDetailsDTO.getFlightId());
        if (optionalFlight.isPresent()) {
            throw new FlightServiceException(environment.getProperty(InfyGoConstants.FLIGHT_ALREADY_EXISTS.toString()));
        }

        FlightDetails savedFlight = flightRepository.save(modelMapper.map(flightDetailsDTO, FlightDetails.class));

        return environment.getProperty(InfyGoConstants.FLIGHT_ADDED_MESSAGE.toString()) + " "
                + savedFlight.getFlightId();
    }

    @Override
    public List<String> getFlightSourceCities() {
        List<String> sourceCities = flightRepository.findAll().stream().map(flight -> flight.getSource()).distinct()
                .collect(Collectors.toList());

        return sourceCities;
    }

    @Override
    public List<String> getFlightDestinationCities() {
        List<String> destinationCities = flightRepository.findAll().stream().map(flight -> flight.getDestination())
                .distinct().collect(Collectors.toList());

        return destinationCities;
    }

    @Override
    public List<FlightDetails> getFlights(String source, String destination, String date) throws ParseException {
        Date formatedDate = Date.valueOf(date);
        return flightRepository.findBySourceAndDestinationAndFlightAvailableDate(source, destination, formatedDate);

    }

    @Override
    public FlightDetails getFlightDetails(String flightId) {
        return flightRepository.findById(flightId).get();
    }

}

