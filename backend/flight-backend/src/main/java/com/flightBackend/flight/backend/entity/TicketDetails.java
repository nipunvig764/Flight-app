package com.flightBackend.flight.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class TicketDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pnr;
    private Date bookingDate;
    private Date departureDate;
    private String departureTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private FlightDetails flightDetails;
    private Integer noOfSeats;
    private Double totalFare;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;
    @OneToMany(mappedBy = "ticketDetails")
    private List<PassengerDetails> passengerDetails;

    public void setFlightDetails(FlightDetails flightDetails) {
        this.flightDetails = flightDetails;
    }

    public List<PassengerDetails> getPassengerDetails() {
        return passengerDetails;
    }

    public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
        this.passengerDetails = passengerDetails;
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

    public FlightDetails getFlightDetails() {
        return flightDetails;
    }

    public void setFlightId(FlightDetails flightDetails) {
        this.flightDetails = flightDetails;
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

    public UserDetail getUserDetails() {
        return userDetail;
    }

    public void setUserDetails(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

}
