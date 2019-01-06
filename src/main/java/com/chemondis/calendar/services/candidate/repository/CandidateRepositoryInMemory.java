package com.chemondis.calendar.services.candidate.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.storage.Storage;

@Component("candidate_repository_inmemory")
public class CandidateRepositoryInMemory extends CandidateRepository {

    protected Storage<Candidate> storage;

    @Autowired
    public CandidateRepositoryInMemory(@Qualifier("candidates_inmemory_storage") Storage<Candidate> storage) {
        this.storage = storage;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return new ArrayList<>(storage.getAllObjects().values());
    }

    @Override
    public Candidate saveCandidate(String name, Set<InterviewAvailability> availability) {
        // the ID will be generated based on id of the last entry
        return storage.saveObject(new Candidate(null, name, availability));
    }

    @Override
    public Candidate updateCandidate(long id, String name, Set<InterviewAvailability> availability) {
        return storage.updateObject(new Candidate(id, name, availability));
    }

    @Override
    public Candidate getCandidateById(long id) {
        return storage.getObjectByPrimaryIndex(id);
    }
}
