// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.resource;

import com.tool.emailbot.application.EmailApplicationService;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.EmailbotException;
import com.tool.emailbot.domain.service.RegisterEmailService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Identity resource to interact with other SOA systems.
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
    @Path("/person")
    public boolean registerUser(RegisterEmailService emailResource) throws
            EmailbotException {
                applicationService.registerEmailRequest(null);
        return true;
    }
}
