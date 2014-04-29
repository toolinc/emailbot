// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.application.command.WorkerInformationCommand;
import com.tool.emailbot.common.AssertionConcern;

/**
 * Specifies a echo Restful service to emulate the functionality required for the use case #2
 * step #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class WorkerInfoResourceEcho extends AssertionConcern {

    public WorkerInformationCommand retrieveWorkerInformation(WorkerInformationCommand command) {
        return command;
    }
}
