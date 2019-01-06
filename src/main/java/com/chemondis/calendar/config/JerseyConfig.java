package com.chemondis.calendar.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.chemondis.calendar.controllers.CandidatesController;
import com.chemondis.calendar.controllers.InterviewAvailabilityController;
import com.chemondis.calendar.controllers.InterviewersController;
import com.chemondis.calendar.controllers.errorhandling.BadRequestMapper;
import com.chemondis.calendar.controllers.errorhandling.JsonMappingErrorHandler;
import com.chemondis.calendar.controllers.errorhandling.NotFoundMapper;
import com.chemondis.calendar.controllers.errorhandling.ServerErrorMapper;
import com.chemondis.calendar.controllers.errorhandling.UnprocessableEntityMapper;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // Controllers
        register(InterviewersController.class);
        register(CandidatesController.class);
        register(InterviewAvailabilityController.class);

        // Error handling
        register(ServerErrorMapper.class);
        register(NotFoundMapper.class);
        register(BadRequestMapper.class);
        register(JsonMappingErrorHandler.class);
        register(UnprocessableEntityMapper.class);
    }
}
