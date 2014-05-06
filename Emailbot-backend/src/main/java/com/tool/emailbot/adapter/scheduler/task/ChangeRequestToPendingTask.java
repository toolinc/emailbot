// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler.task;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.service.ValidateWorkerInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * The aim of the service is schedule the Validation of the worker information for the Use Case #2.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ChangeRequestToPendingTask extends AssertionConcern implements Task {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PeticionRepository repository;
    private final ValidateWorkerInfoService workerInfoService;
    private final Integer fetchSize = 10;

    @Inject
    public ChangeRequestToPendingTask(PeticionRepository repository,
                                      ValidateWorkerInfoService workerInfoService) {
        assertArgumentNotNull(repository, "The Request repository is null.");
        assertArgumentNotNull(workerInfoService, "The Worker Info Service  is null.");
        this.repository = repository;
        this.workerInfoService = workerInfoService;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void task() {
        List<Peticion> peticiones = repository.find(Estatus.SOLICITUD, fetchSize);
        logger.debug(String.format("Peticiones [%d].", peticiones.size()));
        for (Peticion peticion : peticiones) {
            try {
                processRequest(peticion);
            } catch (Exception exc) {
                logger.warn(String.format("The peticion [%s] was not process successfully.",
                        peticion.getId()), exc);
            }
        }
    }

    /**
     * Specifies the task that will be performed as part of a Job.
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void processRequest(Peticion peticion) {
        workerInfoService.validateWorkerInformation(peticion.getTrabajador());
        peticion.transitionFromSolicitud();
        repository.update(peticion);
        logger.debug(String.format("Estatus [%s].", peticion.getEstatus()));
    }
}
