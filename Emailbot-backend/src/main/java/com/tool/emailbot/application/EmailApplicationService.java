// Copyright 2014 Tool Inc.

package com.tool.emailbot.application;

import com.tool.emailbot.application.command.RegisterEmailCommand;
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
    public boolean registerEmailRequest(RegisterEmailCommand command) throws EmailbotException {
        Peticion peticion = Peticion.Builder.newBuilder()
                .setEmail(command.getEmail())
                .setUsername(command.getUsername())
                .setTrabajador(Trabajador.Builder.newBuilder()
                        .setNumeroTrabajador(command.getWorkerNumber())
                        .setDirector(true)
                        .setDependencia(new Dependencia.Builder()
                                .setNombre(command.getDependencyName())
                                .setAbreviacion(command.getDependencyCode())
                                .build())
                        .setPersona(Persona.Builder.newBuilder()
                                .setInformacionContacto(InformacionContacto.Builder.newBuilder()
                                        .setEmail(command.getEmail()))
                                .setApellidoMaterno(command.getMotherLastName())
                                .setApellidoPaterno(command.getFatherLastName())
                                .setHomoclave(command.getHomoclave())
                                .setNombre(command.getName())
                                .setFechaNacimiento(command.getDateOfBirth()))
                        .build())
                .build();

        registerEmailService.registerEmailRequest(peticion);
        return true;
    }
}
