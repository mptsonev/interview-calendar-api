package com.chemondis.calendar.models;

import java.util.Set;

import com.chemondis.calendar.storage.Storable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Interviewer implements Storable {

    private Long id;
    private String name;
    private Set<InterviewAvailability> availability;

    public Interviewer(Long id, String name, Set<InterviewAvailability> availability) {
        this.id = id;
        this.name = name;
        this.availability = availability;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public Set<InterviewAvailability> getAvailability() {
        return availability;
    }

}
