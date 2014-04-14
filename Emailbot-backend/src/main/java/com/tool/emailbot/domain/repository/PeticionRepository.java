// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.domain.model.Peticion;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface PeticionRepository {
    void create(Peticion trabajador);

    Peticion update(Peticion peticion);

    void delete(Peticion trabajador);

    public Peticion findBy(String numeroTrabajador);
}
