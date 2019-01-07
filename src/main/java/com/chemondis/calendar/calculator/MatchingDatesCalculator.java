package com.chemondis.calendar.calculator;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.InterviewAvailability;

import jersey.repackaged.com.google.common.collect.Sets;

@Component
public class MatchingDatesCalculator {

    public Set<Date> calculateMatchingDates(InterviewAvailability candidateAvailabilityItem, InterviewAvailability interviewAvailabilityItem) {
        return Sets.intersection(candidateAvailabilityItem.getDays(), interviewAvailabilityItem.getDays());

    }
}
