package com.chemondis.calendar.storage;

import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.Interviewer;

@Component("interviewers_inmemory_storage")
public class InterviewersInMemoryStorage extends InMemoryStorage<Interviewer> {

}
