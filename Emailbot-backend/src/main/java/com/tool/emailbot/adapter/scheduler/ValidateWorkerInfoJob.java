// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.service.ValidateWorkerInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ejb.Schedule;
import javax.transaction.Transactional;

/**
 * The aim of the service is schedule the Validation of the worker information for the Use Case #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ValidateWorkerInfoJob extends AssertionConcern {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PeticionRepository repository;
    private final ValidateWorkerInfoService workerInfoService;
    private final Integer fetchSize = 10;

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
        List<Peticion> peticiones = repository.find(Estatus.SOLICITUD, fetchSize);
        for (Peticion peticion : peticiones) {
            try {
                processPeticion(peticion);
            } catch (Exception exc) {
                logger.warn(String.format("The peticion [%s] was not process successfully.",
                        peticion.getId()));
            }
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    private void processPeticion(Peticion peticion) {
        workerInfoService.validateWorkerInformation(peticion.getTrabajador());
        peticion.transitionFromSolicitud();
    }
}
