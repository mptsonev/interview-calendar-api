package com.chemondis.calendar.controllers.requestmodels;

import java.util.Set;

import com.chemondis.calendar.models.InterviewAvailability;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveAvailabilityRequest {

    @JsonProperty
    private String name;
    @JsonProperty
    private Set<InterviewAvailability> availability;

    public String getName() {
        return name;
    }

    public Set<InterviewAvailability> getAvailability() {
        return availability;
    }
}
