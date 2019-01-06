package com.chemondis.calendar.controllers.requestmodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetInterviewAvailabilityRequest {

    @JsonProperty
    private long candidateId;
    @JsonProperty
    private List<Long> interviewerIds;

    public long getCandidateId() {
        return candidateId;
    }

    public List<Long> getInterviewerIds() {
        return interviewerIds;
    }
}
