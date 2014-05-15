// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler.task;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * The aim of the service is to create an email per request for the Use Case #5.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ChangeRequestToCreatedTask extends AssertionConcern implements Task{
     private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PeticionRepository repository;
    private final NotificationService notificationService;
    private final Integer fetchSize;

    @Inject
    public ChangeRequestToCreatedTask(PeticionRepository repository,
                                       NotificationService notificationService,
                                       @Named("fetchSize") int fetchSize) {
        assertArgumentNotNull(repository, "The Request repository is null.");
        assertArgumentNotNull(notificationService, "The Worker Info Service  is null.");
        assertArgumentTrue(fetchSize > 0,
                "The number of elements to fetch should be bigger than zero.");
        this.repository = repository;
        this.notificationService = notificationService;
        this.fetchSize = fetchSize;
    }

    /**
     * Specifies the task that will be performed as part of a Job.
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void task() {
        List<Peticion> peticiones = repository.find(Estatus.APROVADA, fetchSize);
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
     * Process a single request at the time.
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void processRequest(Peticion peticion) {
        try {
            peticion.transitionFromCreada();
            repository.update(peticion);

        } catch (Exception exc) {
            logger.warn(String.format("The peticion [%s] was not process successfully.",
                    peticion.getId()), exc);
        }
        logger.debug(String.format("Estatus [%s].", peticion.getEstatus()));
    }
}
