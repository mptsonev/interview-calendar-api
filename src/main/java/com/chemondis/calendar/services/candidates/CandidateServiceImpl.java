package com.chemondis.calendar.services.candidates;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.services.candidate.repository.CandidateRepository;

@Component
public class CandidateServiceImpl implements CandidateService {

    protected CandidateRepository repository;

    @Autowired
    public CandidateServiceImpl(@Qualifier("candidate_repository_inmemory") CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return repository.getAllCandidates();
    }

    @Override
    public Candidate createCandidate(String name, Set<InterviewAvailability> availability) {
        return repository.saveCandidate(name, availability);
    }

    @Override
    public Candidate updateCandidate(long id, String name, Set<InterviewAvailability> availability)
        throws CandidateNotFoundException {
        Candidate candidate = repository.updateCandidate(id, name, availability);
        if (candidate == null) {
            throw new CandidateNotFoundException("Candidate with id: " + id + " not found!");
        }
        return candidate;
    }

    @Override
    public Candidate getCandidateById(long id)
        throws CandidateNotFoundException {
        Candidate candidate = repository.getCandidateById(id);
        if (candidate == null) {
            throw new CandidateNotFoundException("Candidate with id: " + id + " not found!");
        }
        return candidate;
    }

}
