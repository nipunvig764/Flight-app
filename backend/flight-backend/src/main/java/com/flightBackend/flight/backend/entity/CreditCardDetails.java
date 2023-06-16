package com.flightBackend.flight.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CreditCardDetails {
    @Id
    private String cardNumber;
    private String apin;
    private String cardHolderName;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;
    private String totalBill;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;

    public UserDetail getUserDetails() {
        return userDetail;
    }

    public void setUserDetails(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getApin() {
        return apin;
    }

    public void setApin(String apin) {
        this.apin = apin;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }

}



