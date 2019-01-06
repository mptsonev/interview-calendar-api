package com.chemondis.calendar.services.interviewer.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;
import com.chemondis.calendar.storage.Storage;

@Component("interviewer_repository_inmemory")
public class InterviewerRepositoryInMemory extends InterviewerRepository {

    protected Storage<Interviewer> storage;

    @Autowired
    public InterviewerRepositoryInMemory(@Qualifier("interviewers_inmemory_storage") Storage<Interviewer> storage) {
        this.storage = storage;
    }

    @Override
    public List<Interviewer> getAllInterviewers() {
        return new ArrayList<>(storage.getAllObjects().values());
    }

    @Override
    public Interviewer saveInterviewer(String name, Set<InterviewAvailability> availability) {
        // the ID will be generated based on id of the last entry
        return storage.saveObject(new Interviewer(null, name, availability));
    }

    @Override
    public Interviewer updateInterviewer(long id, String name, Set<InterviewAvailability> availability) {
        return storage.updateObject(new Interviewer(id, name, availability));
    }

    @Override
    public Interviewer getInterviewerById(long id) {
        return storage.getObjectByPrimaryIndex(id);
    }
}
