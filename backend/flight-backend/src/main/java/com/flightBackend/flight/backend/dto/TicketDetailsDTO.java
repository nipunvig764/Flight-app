package com.flightBackend.flight.backend.dto;

import java.util.Date;
import java.util.List;

public class TicketDetailsDTO {

    private Integer pnr;
    private Date bookingDate;
    private Date departureDate;
    private String departureTime;
    private String flightId;
    private Integer noOfSeats;
    private Double totalFare;
    private String userId;
    private List<PassengerDetailsDTO> passengers;

    public List<PassengerDetailsDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDetailsDTO> passengers) {
        this.passengers = passengers;
    }

    public Integer getPnr() {
        return pnr;
    }

    public void setPnr(Integer pnr) {
        this.pnr = pnr;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Double totalFare) {
        this.totalFare = totalFare;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
