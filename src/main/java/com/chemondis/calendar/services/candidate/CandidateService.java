package com.chemondis.calendar.services.candidate;

import java.util.List;
import java.util.Set;

import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;

public interface CandidateService {

    List<Candidate> getAllCandidates();

    Candidate createCandidate(String name, Set<InterviewAvailability> availability);

    Candidate updateCandidate(long id, String name, Set<InterviewAvailability> availability)
        throws CandidateNotFoundException;

    Candidate getCandidateById(long id)
        throws CandidateNotFoundException;
}
