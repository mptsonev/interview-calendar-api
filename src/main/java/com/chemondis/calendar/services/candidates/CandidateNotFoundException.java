package com.chemondis.calendar.services.candidates;

import javax.ws.rs.NotFoundException;

public class CandidateNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1L;

    public CandidateNotFoundException() {
        super();
    }

    public CandidateNotFoundException(String message) {
        super(message);
    }
}
