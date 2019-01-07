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

    // Default constructor needed for deserialization
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((days == null) ? 0 : days.hashCode());
        result = prime * result + from;
        result = prime * result + to;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InterviewAvailability other = (InterviewAvailability) obj;
        if (days == null) {
            if (other.days != null)
                return false;
        } else if (!days.equals(other.days))
            return false;
        if (from != other.from)
            return false;
        if (to != other.to)
            return false;
        return true;
    }

}
