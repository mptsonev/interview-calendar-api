package com.chemondis.calendar.services.interviewer;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;
import com.chemondis.calendar.services.interviewer.repository.InterviewerRepository;

@Component
public class InterviewerServiceImpl implements InterviewerService {

    protected InterviewerRepository repository;

    @Autowired
    public InterviewerServiceImpl(@Qualifier("interviewer_repository_inmemory") InterviewerRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<Interviewer> getAllInterviewers() {
        return repository.getAllInterviewers();
    }

    @Override
    public Interviewer createInterviewer(String name, Set<InterviewAvailability> availability) {
        return repository.saveInterviewer(name, availability);
    }

    @Override
    public Interviewer updateInterviewer(long id, String name, Set<InterviewAvailability> availability)
        throws InterviewerNotFoundException {
        Interviewer interviewer = repository.updateInterviewer(id, name, availability);
        if (interviewer == null) {
            throw new InterviewerNotFoundException("Interviewer with id: " + id + " not found!");
        }
        return interviewer;
    }

    @Override
    public Interviewer getInterviewerById(long id)
        throws InterviewerNotFoundException {
        Interviewer interviewer = repository.getInterviewerById(id);
        if (interviewer == null) {
            throw new InterviewerNotFoundException("Interviewer with id: " + id + " not found!");
        }
        return interviewer;
    }

}
