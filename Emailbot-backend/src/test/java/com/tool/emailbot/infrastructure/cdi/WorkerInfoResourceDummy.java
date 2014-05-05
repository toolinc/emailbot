// Copyright 2014 Tool Inc.

package com.tool.emailbot.infrastructure.cdi;

import com.tool.emailbot.application.command.WorkerInformationCommand;
import com.tool.emailbot.resource.WorkerInfoResource;

/**
 * Invokes the Worker Web Service to retrieve the Worker Information.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class WorkerInfoResourceDummy implements WorkerInfoResource {
     
    /**
     * Retrieves the worker information from the external service.
     *
     * @param command specifies the Worker Number from which its information is required.
     * @return the worker information
     */
    public WorkerInformationCommand retrieveWorkerInfo(WorkerInformationCommand command) {
        return command;
    }
}

