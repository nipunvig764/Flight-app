package com.flightBackend.flight.backend.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.flightBackend.flight.backend.dto.FlightDetailsDTO;
import com.flightBackend.flight.backend.entity.FlightDetails;
import com.flightBackend.flight.backend.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/flights")
@CrossOrigin
@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/admin/add")
    public ResponseEntity<String> addFlights(@RequestBody FlightDetailsDTO flightDetailsDTO) {
        String message = flightService.addFlight(flightDetailsDTO);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/sources")
    public ResponseEntity<List<String>> flightsSourceCities() {
        List<String> sourceCityNames = flightService.getFlightSourceCities();
        return ResponseEntity.ok(sourceCityNames);
    }

    @GetMapping("/destination")
    public ResponseEntity<List<String>> flightDestinationCities() {
        List<String> destinationCityNames = flightService.getFlightDestinationCities();
        return ResponseEntity.ok(destinationCityNames);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<FlightDetails>> getFlights(@MatrixVariable(pathVar = "query") Map<String, String> map)
            throws ParseException {
        List<FlightDetails> flights = flightService.getFlights(map.get("source"), map.get("destination"),
                map.get("date"));
        return ResponseEntity.ok(flights);
    }
    @GetMapping("get/{flightId}")
    public ResponseEntity<FlightDetails> getFlightDetails(@PathVariable("flightId")String flightId){
        return ResponseEntity.ok(flightService.getFlightDetails(flightId));
    }

}



