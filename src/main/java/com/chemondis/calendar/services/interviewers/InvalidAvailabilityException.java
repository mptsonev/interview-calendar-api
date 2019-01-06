package com.chemondis.calendar.services.interviewers;

public class InvalidAvailabilityException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidAvailabilityException() {
        super();
    }

    public InvalidAvailabilityException(String message) {
        super(message);
    }
}
