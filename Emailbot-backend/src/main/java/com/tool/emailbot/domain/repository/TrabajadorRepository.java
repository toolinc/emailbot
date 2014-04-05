// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.domain.model.Trabajador;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface TrabajadorRepository {

    void create(Trabajador trabajador);

    Trabajador update(Trabajador trabajador);

    void delete(Trabajador trabajador);

    public Trabajador findBy(String numeroTrabajador);
}
