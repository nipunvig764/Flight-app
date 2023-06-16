package com.flightBackend.flight.backend.dto;

public class PassengerDetailsDTO {

    private String passengerId;
    private String passengerAge;
    private String passengerGender;
    private String passengerName;
    private Integer ticketPnr;
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

    public Integer getTicketPnr() {
        return ticketPnr;
    }

    public void setTicketPnr(Integer ticketPnr) {
        this.ticketPnr = ticketPnr;
    }

}



