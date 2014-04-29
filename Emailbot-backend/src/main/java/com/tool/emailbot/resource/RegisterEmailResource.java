// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.application.EmailApplicationService;
import com.tool.emailbot.application.command.RegisterEmailCommand;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.EmailbotException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Register resource to interact with other SOA systems.
 *
 *  @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Path("/register/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterEmailResource extends AssertionConcern {

    @Inject
    private EmailApplicationService applicationService;

    @POST
    @Path("/worker")
    public boolean registerWorker(RegisterEmailCommand command) throws EmailbotException {
        applicationService.registerEmailRequest(command);
        return true;
    }
}
