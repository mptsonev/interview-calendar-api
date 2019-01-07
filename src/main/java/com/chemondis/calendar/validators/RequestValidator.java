package com.chemondis.calendar.validators;

import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.chemondis.calendar.controllers.requestmodels.SaveAvailabilityRequest;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.services.interviewer.InvalidAvailabilityException;

public class RequestValidator {

    public static void validate(SaveAvailabilityRequest saveInterviewerRequest)
        throws InvalidAvailabilityException {
        validateAvailability(saveInterviewerRequest.getAvailability());
    }

    private static void validateAvailability(Set<InterviewAvailability> availability)
        throws InvalidAvailabilityException {
        if (CollectionUtils.isEmpty(availability)) {
            return;
        }
        for (InterviewAvailability availabilityItem : availability) {
            validateAvailabilityItem(availabilityItem);
        }
    }

    private static void validateAvailabilityItem(InterviewAvailability availabilityItem)
        throws InvalidAvailabilityException {

        int from = availabilityItem.getFrom();
        if (from < 0 || from > 24) {
            throw new InvalidAvailabilityException("Invalid availability time set. Check the 'from' field");
        }

        int to = availabilityItem.getTo();
        if (to < 0 || to > 24) {
            throw new InvalidAvailabilityException("Invalid availability time set. Check the 'to' field" );
        }

    }

}
