package com.chemondis.calendar.services.interviewer;

import java.util.List;
import java.util.Set;

import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;

public interface InterviewerService {

    List<Interviewer> getAllInterviewers();

    Interviewer createInterviewer(String name, Set<InterviewAvailability> availability);

    Interviewer updateInterviewer(long id, String name, Set<InterviewAvailability> availability)
        throws InterviewerNotFoundException;

    Interviewer getInterviewerById(long id)
        throws InterviewerNotFoundException;
}
