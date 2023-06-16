package com.flightBackend.flight.backend.exceptions;

public class ErrorMessage {
    private String message;
    private Integer errorStatusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorStatusCode() {
        return errorStatusCode;
    }

    public void setErrorStatusCode(Integer errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }

}
