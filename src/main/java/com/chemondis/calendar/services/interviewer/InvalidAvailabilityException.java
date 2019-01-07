package com.chemondis.calendar.services.interviewer;

import javax.ws.rs.BadRequestException;

public class InvalidAvailabilityException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public InvalidAvailabilityException() {
        super();
    }

    public InvalidAvailabilityException(String message) {
        super(message);
    }
}
