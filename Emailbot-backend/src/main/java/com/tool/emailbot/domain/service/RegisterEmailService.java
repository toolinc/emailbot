// Copyright 2014 Tool Inc.
package com.tool.emailbot.domain.service;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.EmailbotException;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.DependenciaRepository;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.domain.repository.TrabajadorRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * The aim of the service is to register an email request from a worker.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterEmailService extends AssertionConcern {

    private final TrabajadorRepository trabajadorRepository;
    private final PeticionRepository peticionRepository;
    private final DependenciaRepository dependenciaRepository;

    private static final String USER_NAME_EXIST
	    = "com.tool.emailbot.domain.service.RegisterEmailService.exist";
    private static final String USER_NAME_NOT_EXIST
	    = "com.tool.emailbot.domain.service.RegisterEmailService.notExist";

    @Inject
    public RegisterEmailService(TrabajadorRepository trabajadorRepository,
	    PeticionRepository peticionRepository,
	    DependenciaRepository dependenciaRepository) {
	assertArgumentNotNull(trabajadorRepository, "The Worker Repository is null.");
	assertArgumentNotNull(trabajadorRepository, "The Request Repository is null.");
	this.trabajadorRepository = trabajadorRepository;
	this.peticionRepository = peticionRepository;
	this.dependenciaRepository = dependenciaRepository;
    }

    /**
     * Registers a new {@link com.tool.emailbot.domain.model.Peticion} on the system.
     *
     * @param peticion the request that will be stored.
     * @throws EmailbotException if a request cannot be created
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void registerEmailRequest(Peticion peticion)
	    throws EmailbotException {
	Trabajador trabajador = trabajadorRepository.findBy(peticion.getTrabajador()
		.getNumeroTrabajador());
	if (trabajador == null) {
	    Dependencia dependencia = new Dependencia.Builder().build();
	    peticion.getTrabajador().setDependencia(dependencia);
	    dependenciaRepository.create(dependencia);
	    peticionRepository.create(peticion);
	} else {
	    throw EmailbotException.Builder.newBuilder()
		    .setMessage(USER_NAME_EXIST, peticion.getTrabajador().getNumeroTrabajador())
		    .build();
	}
    }
}
