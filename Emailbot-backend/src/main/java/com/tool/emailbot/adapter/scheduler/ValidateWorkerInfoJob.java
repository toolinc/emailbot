// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.service.ValidateWorkerInfoService;

/**
 * The aim of the service is schedule the Validation of the worker information for the Use Case #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
//@Schedules()
public class ValidateWorkerInfoJob extends AssertionConcern {

    private final PeticionRepository repository;
    private final ValidateWorkerInfoService workerInfoService;

    public ValidateWorkerInfoJob(PeticionRepository repository,
                                 ValidateWorkerInfoService workerInfoService) {
        assertArgumentNotNull(repository, "The Request repository is null.");
        assertArgumentNotNull(workerInfoService, "The Worker Info Service  is null.");
        this.repository = repository;
        this.workerInfoService = workerInfoService;
    }

    /**
     * Specifies the task that will be performed as part of a Job.
     */
    public void run() {
    }
}
