package com.chemondis.calendar.availability.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;

@Component
public class AvailabilityCalculator {

    public Set<InterviewAvailability> calculateMatchingAvailability(Candidate candidate, List<Interviewer> interviewers) {
        Set<InterviewAvailability> availability = new HashSet<>();
        for (Interviewer interviewer : interviewers) {
            availability.addAll(calculateMatchingAvailability(candidate, interviewer));
        }

        return availability;
    }

    private Set<InterviewAvailability> calculateMatchingAvailability(Candidate candidate, Interviewer interviewer) {
        Set<InterviewAvailability> availability = new HashSet<>();

        Set<InterviewAvailability> candidateAvailability = candidate.getAvailability();
        Set<InterviewAvailability> interviewerAvailability = interviewer.getAvailability();

        for (InterviewAvailability candidateAvailabilityItem : candidateAvailability) {
            for (InterviewAvailability interviewAvailabilityItem : interviewerAvailability) {
                Set<Integer> matchingHours = calculateMatchingHours(candidateAvailabilityItem, interviewAvailabilityItem);
                if (matchingHours.isEmpty()) {
                    continue;
                }
                Set<Date> matchingDates = calculateMatchingDates(candidateAvailabilityItem, interviewAvailabilityItem);
                if (matchingDates.isEmpty()) {
                    continue;
                }

                List<InterviewAvailability> matchingAvailability = getMatchingAvailability(matchingHours, matchingDates);
                availability.addAll(matchingAvailability);

            }
        }

        return availability;

    }

    private List<InterviewAvailability> getMatchingAvailability(Set<Integer> matchingHours, Set<Date> matchingDates) {
        List<InterviewAvailability> availability = new ArrayList<>();
        for (Integer matchingHour : matchingHours) {
            for (Date matchingDate : matchingDates) {
                availability.add(new InterviewAvailability(matchingHour, matchingHour + 1, Collections.singleton(matchingDate)));
            }
        }
        return availability;
    }

    private Set<Integer> calculateMatchingHours(InterviewAvailability candidateAvailabilityItem, InterviewAvailability interviewAvailabilityItem) {

        int candidateFrom = candidateAvailabilityItem.getFrom();
        int candidateTo = candidateAvailabilityItem.getTo();
        int interviewerFrom = interviewAvailabilityItem.getFrom();
        int interviewerTo = interviewAvailabilityItem.getTo();

        Set<Integer> matchingHoursList = new HashSet<>();

        int firstPossibleHour = Math.max(candidateFrom, interviewerFrom);
        int lastPossibleHour = Math.min(candidateTo, interviewerTo);
        while (firstPossibleHour < lastPossibleHour) {
            matchingHoursList.add(firstPossibleHour++);
        }

        return matchingHoursList;
    }

    private Set<Date> calculateMatchingDates(InterviewAvailability candidateAvailabilityItem, InterviewAvailability interviewAvailabilityItem) {

        Set<Date> candidateAvailableDays = candidateAvailabilityItem.getDays();
        Set<Date> interviewerAvailableDays = interviewAvailabilityItem.getDays();

        return candidateAvailableDays.stream().filter(d -> interviewerAvailableDays.contains(d)).collect(Collectors.toSet());

    }

}
