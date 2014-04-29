// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.service;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.DependenciaRepository;
import com.tool.emailbot.domain.repository.PeticionRepository;
import com.tool.emailbot.resource.WorkerInfoResource;

/**
 * The aim of the service is to validate the worker information of a new request of email service.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ValidateWorkerInfoService extends AssertionConcern {

    private final WorkerInfoResource resource;
    private final PeticionRepository peticionRepository;
    private final DependenciaRepository dependenciaRepository;

    public ValidateWorkerInfoService(WorkerInfoResource resource,
                                     PeticionRepository peticionRepository,
                                     DependenciaRepository dependenciaRepository) {
        assertArgumentNotNull(resource, "The Worker Information client is null.");
        assertArgumentNotNull(peticionRepository, "The Request repository is null.");
        assertArgumentNotNull(dependenciaRepository, "The Dependency repository is null.");
        this.resource = resource;
        this.peticionRepository = peticionRepository;
        this.dependenciaRepository = dependenciaRepository;
    }

    /**
     * Validates the worker information o a request in
     * {@code com.tool.emailbot.domain.model.Estatus.SOLICITUD}.
     *
     * @param peticion the request that will be validated
     */
    public void validateWorkerInformation(Peticion peticion) {
    }
}
