// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;

import java.util.List;

/**
 * Specifies the operations of the Request repository.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface PeticionRepository {

    /**
     * @param trabajador
     */
    void create(Peticion trabajador);

    Peticion update(Peticion peticion);

    void delete(Peticion trabajador);

    Peticion findBy(String numeroTrabajador);

    /**
     * Retrieves the desired number of request from the top of the queue from the persistent
     * storage. This behaves as queue pop operation from the storage.
     *
     * @param estatus specifies the status of the request that will be retrieved
     * @param size    specifies the number of the request that will be retrieved
     * @return the list of request retrieved
     */
    List<Peticion> find(Estatus estatus, int size);
}
