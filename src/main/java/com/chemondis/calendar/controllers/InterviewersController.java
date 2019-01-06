package com.chemondis.calendar.controllers;

import java.util.Set;

import javax.ws.rs.BadRequestException;
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
import com.chemondis.calendar.models.InterviewAvailability;
import com.chemondis.calendar.models.Interviewer;
import com.chemondis.calendar.services.interviewer.InterviewerService;
import com.chemondis.calendar.services.interviewer.InvalidAvailabilityException;
import com.chemondis.calendar.validators.RequestValidator;

@Component
@Path("/interviewers")
public class InterviewersController {

    @Autowired
    private InterviewerService interviewerService;

    @Context
    private UriInfo uri;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInterviewers() {
        return Response.status(200).entity(interviewerService.getAllInterviewers()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewInterviewer(SaveAvailabilityRequest interviewerRequest) {
        try {
            RequestValidator.validate(interviewerRequest);
        } catch (InvalidAvailabilityException e) {
            throw new BadRequestException(e.getMessage());
        }
        Interviewer interviewer = interviewerService.createInterviewer(interviewerRequest.getName(), interviewerRequest.getAvailability());
        return Response.status(201).header("Location", uri.getBaseUri() + "interviewers/" + interviewer.getId()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateInterviewer(@PathParam("id") long id, SaveAvailabilityRequest updateInterviewerRequest) {
        try {
            RequestValidator.validate(updateInterviewerRequest);
        } catch (InvalidAvailabilityException e) {
            throw new BadRequestException(e.getMessage());
        }
        String name = updateInterviewerRequest.getName();
        Set<InterviewAvailability> availability = updateInterviewerRequest.getAvailability();
        Interviewer interviewer = interviewerService.updateInterviewer(id, name, availability);
        return Response.status(201).header("Location", uri.getBaseUri() + "interviewers/" + interviewer.getId()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getInterviewerById(@PathParam("id") long id)
        throws Exception {
        return Response.status(200).entity(interviewerService.getInterviewerById(id)).build();
    }

}
