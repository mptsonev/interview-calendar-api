package com.chemondis.calendar.services.interviewer;

import javax.ws.rs.NotFoundException;

public class InterviewerNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1L;

    public InterviewerNotFoundException() {
        super();
    }

    public InterviewerNotFoundException(String message) {
        super(message);
    }
}
