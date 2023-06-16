package com.flightBackend.flight.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.flightBackend.flight.backend.services.IdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
public class PassengerDetails {
    @Id
    @GenericGenerator(name = "passenger_id", strategy = "com.flightBackend.flight.backend.services.IdGenerator",
            parameters = {
                    @Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1000"),
                    @Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "P-"),
                    @Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    @GeneratedValue(generator = "passenger_id")
    private String passengerId;
    private String passengerAge;
    private String passengerGender;
    private String passengerName;
    @ManyToOne(cascade = CascadeType.ALL)
    private TicketDetails ticketDetails;

    private boolean emailAcknowledgement;

    public boolean isEmailAcknowledgement() {
        return emailAcknowledgement;
    }

    public void setEmailAcknowledgement(boolean emailAcknowledgement) {
        this.emailAcknowledgement = emailAcknowledgement;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(String passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public TicketDetails getTicketDetails() {
        return ticketDetails;
    }

    public void setTicketDetails(TicketDetails ticketDetails) {
        this.ticketDetails = ticketDetails;
    }


}
