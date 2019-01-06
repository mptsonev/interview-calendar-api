package com.chemondis.calendar.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.calculator.AvailabilityCalculator;
import com.chemondis.calendar.controllers.requestmodels.GetInterviewAvailabilityRequest;
import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;
import com.chemondis.calendar.services.candidate.CandidateService;
import com.chemondis.calendar.services.interviewer.InterviewerService;

@Component
@Path("/availability")
public class InterviewAvailabilityController {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private InterviewerService interviewerService;
    @Autowired
    private AvailabilityCalculator availabilityCalculator;

    @Context
    private UriInfo uri;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getInterviewAvailability(GetInterviewAvailabilityRequest getInterviewAvailabilityRequest) {
        Candidate savedCandidate = candidateService.getCandidateById(getInterviewAvailabilityRequest.getCandidateId());
        List<Interviewer> savedInterviewers = getInterviewers(getInterviewAvailabilityRequest);
        Set<InterviewAvailability> availability = availabilityCalculator.calculateMatchingAvailability(savedCandidate, savedInterviewers);
        return Response.status(200).entity(availability).build();
    }

    private List<Interviewer> getInterviewers(GetInterviewAvailabilityRequest getInterviewAvailabilityRequest) {
        List<Interviewer> interviewers = new ArrayList<>();
        for (long interviewerId : getInterviewAvailabilityRequest.getInterviewerIds()) {
            Interviewer savedInterviewer = interviewerService.getInterviewerById(interviewerId);
            interviewers.add(savedInterviewer);
        }
        return interviewers;
    }

}
