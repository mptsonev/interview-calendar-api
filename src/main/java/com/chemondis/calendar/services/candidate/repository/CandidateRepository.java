package com.chemondis.calendar.services.candidate.repository;

import java.util.List;
import java.util.Set;

import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;

public abstract class CandidateRepository {

    public abstract List<Candidate> getAllCandidates();

    public abstract Candidate saveCandidate(String name, Set<InterviewAvailability> availability);

    public abstract Candidate updateCandidate(long id, String name, Set<InterviewAvailability> availability);

    public abstract Candidate getCandidateById(long id);
}
