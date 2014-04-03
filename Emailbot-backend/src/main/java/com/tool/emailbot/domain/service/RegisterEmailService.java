// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.service;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.EmailbotException;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;

import javax.inject.Inject;

/**
 * The aim of the service is to register an email request from a worker.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterEmailService extends AssertionConcern {

    private final Repository<Trabajador> trabajadorRepository;
    private final Repository<Peticion> peticionRepository;

    @Inject
    public RegisterEmailService(Repository<Trabajador> trabajadorRepository,
                                Repository<Peticion> peticionRepository) {
        assertArgumentNotNull(trabajadorRepository, "The Worker Repository is null.");
        assertArgumentNotNull(trabajadorRepository, "The Request Repository is null.");
        this.trabajadorRepository = trabajadorRepository;
        this.peticionRepository = peticionRepository;
    }

    /**
     * Registers a new {@link com.tool.emailbot.domain.model.Peticion} on the system.
     * 
     * @param peticion the request that will be stored.
     * @throws EmailbotException if a request cannot be created
     */
    public void registerEmailRequest(Peticion peticion) throws EmailbotException {
    }
}
