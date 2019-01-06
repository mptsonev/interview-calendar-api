package com.chemondis.calendar.models;

import java.util.Date;
import java.util.Set;

import com.chemondis.calendar.serialization.DateDeserializer;
import com.chemondis.calendar.serialization.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class InterviewAvailability {

    private int from;
    private int to;
    @JsonDeserialize(contentUsing = DateDeserializer.class)
    @JsonSerialize(contentUsing = DateSerializer.class)
    private Set<Date> days;

    public InterviewAvailability() {}

    public InterviewAvailability(int from, int to, Set<Date> days) {
        this.from = from;
        this.to = to;
        this.days = days;
    }

    @JsonProperty
    public int getFrom() {
        return from;
    }

    @JsonProperty
    public int getTo() {
        return to;
    }

    @JsonProperty
    public Set<Date> getDays() {
        return days;
    }

}
