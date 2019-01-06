package com.chemondis.calendar.storage;

import org.springframework.stereotype.Component;

import com.chemondis.calendar.models.Candidate;

@Component("candidates_inmemory_storage")
public class CandidatesInMemoryStorage extends InMemoryStorage<Candidate> {

}
