package com.chemondis.calendar.services.interviewer.repository;

import java.util.List;
import java.util.Set;

import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;

public abstract class InterviewerRepository {

    public abstract List<Interviewer> getAllInterviewers();

    public abstract Interviewer saveInterviewer(String name, Set<InterviewAvailability> availability);

    public abstract Interviewer updateInterviewer(long id, String name, Set<InterviewAvailability> availability);

    public abstract Interviewer getInterviewerById(long id);
}
