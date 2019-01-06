package com.chemondis.calendar.models;

import java.util.Set;

public class Candidate extends Interviewer {

    public Candidate(Long id, String name, Set<InterviewAvailability> availability) {
        super(id, name, availability);
    }

}
