package com.flightBackend.flight.backend.repository;


import com.flightBackend.flight.backend.entity.PassengerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<PassengerDetails, Integer> {

}
