package com.chemondis.calendar.calculator;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;

@Component
public class AvailabilityCalculator {

    @Autowired
    private MatchingHoursCalculator matchingHoursCalculator;
    @Autowired
    private MatchingDatesCalculator matchingDatesCalculator;

    private static final int INTERVIEW_LENGTH = 1;

    public Set<InterviewAvailability> calculateMatchingAvailability(Candidate candidate, List<Interviewer> interviewers) {
        return interviewers.stream().map(i -> calculateMatchingAvailability(candidate, i)).flatMap(l -> l.stream()).collect(Collectors.toSet());
    }

    private Set<InterviewAvailability> calculateMatchingAvailability(Candidate candidate, Interviewer interviewer) {
        Set<InterviewAvailability> availability = new HashSet<>();

        Set<InterviewAvailability> candidateAvailability = candidate.getAvailability();
        Set<InterviewAvailability> interviewerAvailability = interviewer.getAvailability();

        for (InterviewAvailability candidateAvailabilityItem : candidateAvailability) {
            for (InterviewAvailability interviewAvailabilityItem : interviewerAvailability) {
                availability.addAll(getMatchingAvailability(candidateAvailabilityItem, interviewAvailabilityItem));
            }
        }

        return availability;

    }

    private Set<InterviewAvailability> getMatchingAvailability(InterviewAvailability candidateAvailability, InterviewAvailability interviewerAvailability) {

        Set<Integer> matchingHours = matchingHoursCalculator.calculateMatchingHours(candidateAvailability, interviewerAvailability);
        Set<Date> matchingDates = matchingDatesCalculator.calculateMatchingDates(candidateAvailability, interviewerAvailability);

        Set<InterviewAvailability> availability = new HashSet<>();

        // Return one entry for each available hour
        for (Integer matchingHour : matchingHours) {
            for (Date matchingDate : matchingDates) {
                availability.add(new InterviewAvailability(matchingHour, matchingHour + INTERVIEW_LENGTH, Collections.singleton(matchingDate)));
            }
        }
        return availability;
    }

}
