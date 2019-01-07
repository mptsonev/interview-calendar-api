package com.chemondis.calendar.controllers;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chemondis.calendar.controllers.requestmodels.SaveAvailabilityRequest;
import com.chemondis.calendar.models.Candidate;
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.services.candidate.CandidateService;
import com.chemondis.calendar.validators.RequestValidator;

@Component
@Path("/candidates")
public class CandidatesController {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private RequestValidator requestValidator;

    @Context
    private UriInfo uri;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCandidates() {
        return Response.status(200).entity(candidateService.getAllCandidates()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCandidate(SaveAvailabilityRequest candidateRequest) {
        requestValidator.validate(candidateRequest);
        Candidate candidate = candidateService.createCandidate(candidateRequest.getName(), candidateRequest.getAvailability());
        return Response.status(201).header("Location", uri.getBaseUri() + "candidates/" + candidate.getId()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateCandidate(@PathParam("id") long id, SaveAvailabilityRequest updateCandidateRequest) {
        requestValidator.validate(updateCandidateRequest);
        String name = updateCandidateRequest.getName();
        Set<InterviewAvailability> availability = updateCandidateRequest.getAvailability();
        Candidate candidate = candidateService.updateCandidate(id, name, availability);
        return Response.status(201).header("Location", uri.getBaseUri() + "candidates//" + candidate.getId()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCandidateById(@PathParam("id") long id)
        throws Exception {
        return Response.status(200).entity(candidateService.getCandidateById(id)).build();
    }

}
