// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler.task;

/**
 * Specifies a task that will be executed in a job.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface Task {

    /**
     * The task that will be executed.
     */
    void task();
}
