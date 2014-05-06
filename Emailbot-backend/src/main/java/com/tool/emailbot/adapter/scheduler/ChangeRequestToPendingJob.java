// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler;

import com.tool.emailbot.adapter.scheduler.task.ChangeRequestToPendingTask;
import com.tool.emailbot.common.AssertionConcern;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 * The aim of the service is schedule the Validation of the worker information for the Use Case #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Singleton
public class ChangeRequestToPendingJob extends AssertionConcern {

    @Inject private ChangeRequestToPendingTask task;

    /**
     * Specifies the task that will be performed as part of a Job.
     */
    @Schedule(minute = "*/30", hour = "*", persistent = false)
    public void run() {
        task.task();
    }
}
