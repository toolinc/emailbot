// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.service;

import com.tool.emailbot.application.command.WorkerInformationCommand;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.DependenciaRepository;
import com.tool.emailbot.domain.repository.TrabajadorRepository;
import com.tool.emailbot.resource.WorkerInfoResource;

import javax.transaction.Transactional;

/**
 * The aim of the service is to validate the worker information of a new request of email service.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ValidateWorkerInfoService extends AssertionConcern {

    private final WorkerInfoResource resource;
    private final TrabajadorRepository trabajadorRepository;
    private final DependenciaRepository dependenciaRepository;

    public ValidateWorkerInfoService(WorkerInfoResource resource,
                                     TrabajadorRepository trabajadorRepository,
                                     DependenciaRepository dependenciaRepository) {
        assertArgumentNotNull(resource, "The Worker Information client is null.");
        assertArgumentNotNull(trabajadorRepository, "The Trabajador repository is null.");
        assertArgumentNotNull(dependenciaRepository, "The Dependency repository is null.");
        this.resource = resource;
        this.trabajadorRepository = trabajadorRepository;
        this.dependenciaRepository = dependenciaRepository;
    }

    /**
     * Validates the worker information on a request in
     * {@code com.tool.emailbot.domain.model.Estatus.SOLICITUD}.
     *
     * @param worker the worker that will be validated
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void validateWorkerInformation(Trabajador worker) {
        WorkerInformationCommand command;
        command = new WorkerInformationCommand(worker.getNumeroTrabajador(),
                worker.getSitucionLaboral().isActive(), worker.getDependencia().getAbreviacion(),
                worker.getDependencia().getNombre());
        command = resource.retrieveWorkerInfo(command);
	//si ya existe un dependencia, 
        Dependencia dependencia = new Dependencia.Builder()
                .setAbreviacion(command.getDependencyCode())
                .setNombre(command.getGetDependencyName())
                .build();
        dependenciaRepository.update(dependencia);
        worker.setDependencia(dependencia);
        trabajadorRepository.update(worker);
    }
}
