// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler;

import com.tool.emailbot.adapter.jpa.JpaPeticionRepository;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.service.ValidateWorkerInfoService;

import javax.ejb.Schedule;

/**
 * The aim of the service is schedule the Validation of the worker information for the Use Case #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
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
    @Schedule(minute = "*/30", hour = "*")
    public void run() {
	Peticion peticion = repository.find(Estatus.SOLICITUD, 1).get(1);
	workerInfoService.validateWorkerInformation(peticion);
	peticion.getTrabajador().getDependencia().setAbreviacion(workerInfoService.getCommand().getDependencyCode());
	peticion.getTrabajador().getDependencia().setNombre(workerInfoService.getCommand().getGetDependencyName());
	peticion.setEstatus(Estatus.PENDIENTE);
    }
}
