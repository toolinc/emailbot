// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler;

import com.tool.emailbot.adapter.scheduler.task.Task;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.repository.TrabajadorRepository;
import com.tool.emailbot.domain.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * The aim of the service is schedule the Validation of the worker information for the Use Case #3.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ChangeRequestToNotifiedTask extends AssertionConcern implements Task{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PeticionRepository repository;
    private final TrabajadorRepository trabajadorRepository;
    private final NotificationService emailService;
    private final Integer fetchSize;

    @Inject
    public ChangeRequestToNotifiedTask(PeticionRepository repository,
                                      NotificationService emailService,
				      TrabajadorRepository trabajadorRepository,
                                      @Named("fetchSize") int fetchSize) {
        assertArgumentNotNull(repository, "The Request repository is null.");
	    assertArgumentNotNull(trabajadorRepository,  "The Worker repository is null.");
        assertArgumentNotNull(emailService, "The Worker Info Service  is null.");
        assertArgumentTrue(fetchSize > 0,
                "The number of elements to fetch should be bigger than zero.");
        this.repository = repository;
	this.trabajadorRepository = trabajadorRepository;
        this.emailService = emailService;
        this.fetchSize = fetchSize;
    }

    /**
     * Specifies the task that will be performed as part of a Job.
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void task() {
        List<Peticion> peticiones = repository.find(Estatus.PENDIENTE, fetchSize);
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
	String email = trabajadorRepository.findBy(peticion.getTrabajador().getDependencia());
	boolean emailValidator = emailService.sendEmail(email);
	if (emailValidator) {
	    peticion.transitionFromPendiente();
	}
        repository.update(peticion);
        logger.debug(String.format("Estatus [%s].", peticion.getEstatus()));
    }
}

