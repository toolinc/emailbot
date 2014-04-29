// Copyright 2014 Tool Inc.

package com.tool.emailbot.application;

import com.tool.emailbot.application.command.RegisterEmailCommand;
import com.tool.emailbot.application.command.RegisterPeticion;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.EmailbotException;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.service.RegisterEmailService;

import javax.inject.Inject;

/**
 * Provides all the features related to email robot.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class EmailApplicationService extends AssertionConcern {
    
    private final RegisterEmailService registerEmailService;

    @Inject
    public EmailApplicationService(RegisterEmailService registerEmailService) {
        assertArgumentNotNull(registerEmailService, "The Register Email Service is null.");
        this.registerEmailService = registerEmailService;
    }

    /**
     * Registers a new {@link com.tool.emailbot.application.command.RegisterEmailCommand} on the system.
     *
     * @param command the request that will be stored.
     * @throws EmailbotException if a request cannot be created
     */
    public boolean registerEmailRequest(RegisterPeticion command) throws EmailbotException {
	Peticion peticion = Peticion.Builder.newBuilder()
		.setEmail(command.getPeticionComammand().getEmail())
		.setUsername(command.getPeticionComammand().getUsername())
		.setTrabajador(Trabajador.Builder.newBuilder()
			.setNumeroTrabajador(command.getPeticionComammand().getTrabajador().getNumeroTrabajador())
			.setDirector(true)
			.setDependencia(new Dependencia.Builder()
				.setNombre(command.getPeticionComammand().getTrabajador().getDependencia().getNombre())
				.setAbreviacion(command.getPeticionComammand().getTrabajador().getDependencia().getAbreviacion())
				.build())
			.setPersona(Persona.Builder.newBuilder()
				.setInformacionContacto(InformacionContacto.Builder.newBuilder()
					.setEmail(command.getPeticionComammand().getTrabajador().getPersona().getInformacionContacto().getEmail()))
				.setApellidoMaterno(command.getPeticionComammand().getTrabajador().getPersona().getApellidoMaterno())
				.setApellidoPaterno(command.getPeticionComammand().getTrabajador().getPersona().getApellidoPaterno())
				.setHomoclave(command.getPeticionComammand().getTrabajador().getPersona().getHomoclave())
				.setNombre(command.getPeticionComammand().getTrabajador().getPersona().getNombrePersona())
				.setFechaNacimiento(command.getPeticionComammand().getTrabajador().getPersona().getFechaNacimiento()))
		.build())
	.build();

	registerEmailService.registerEmailRequest(peticion);
	return true;
    }
}
