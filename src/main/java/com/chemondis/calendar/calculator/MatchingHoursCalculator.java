package com.chemondis.calendar.calculator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.InterviewAvailability;

@Component
public class MatchingHoursCalculator {

    public Set<Integer> calculateMatchingHours(InterviewAvailability candidateAvailabilityItem, InterviewAvailability interviewAvailabilityItem) {

        int candidateFrom = candidateAvailabilityItem.getFrom();
        int candidateTo = candidateAvailabilityItem.getTo();

        int interviewerFrom = interviewAvailabilityItem.getFrom();
        int interviewerTo = interviewAvailabilityItem.getTo();

        Set<Integer> matchingHoursSet = new HashSet<>();

        int firstPossibleHour = Math.max(candidateFrom, interviewerFrom);
        int lastPossibleHour = Math.min(candidateTo, interviewerTo);
        while (firstPossibleHour < lastPossibleHour) {
            matchingHoursSet.add(firstPossibleHour++);
        }

        return matchingHoursSet;
    }

}
