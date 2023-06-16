package com.flightBackend.flight.backend.exceptions;

public enum InfyGoConstants {
    USER_ALREADY_EXISTS("user.already.exists"),
    USER_NOT_FOUND("user.not.found"),
    LOAD_BAL_FAILED("user.load.balance.failed"),
    WRONG_PASSWORD("user.password.invalid"),
    INSUFFICIENT_BALANCE("transfer.insufficient.balance"),
    FAILED_UPDATE_PASSWORD("user.update.password.failed"),
    FLIGHT_ADDED_MESSAGE("flight.added.successfully"),
    FLIGHT_ALREADY_EXISTS("flight.already.exists"),
    CARD_ALREADY_EXISTS("card.already.exists"),
    PAYMENT_FAILED("payment.failed"),
    WRONG_CARD_DETAILS("wrong.card.details"),
    NO_FLIGHT_FOUND("flight.not.found"),
    SEATS_NOT_AVAILABLE("seats.not.available");

    private final String type;

    private InfyGoConstants(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}


