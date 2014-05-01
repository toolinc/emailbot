// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.application.EmailApplicationService;
import com.tool.emailbot.application.command.WorkerInformationCommand;
import com.tool.emailbot.common.AssertionConcern;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Specifies a echo Restful service to emulate the functionality required for the use case #2
 * step #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Path("/DGIRE/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkerInfoResourceEcho extends AssertionConcern {
    
    @Inject
    private EmailApplicationService applicationService;

    @POST
    @Path("/findWorker")
    public WorkerInformationCommand retrieveWorkerInformation(WorkerInformationCommand command) {
        return command;
    }
}
