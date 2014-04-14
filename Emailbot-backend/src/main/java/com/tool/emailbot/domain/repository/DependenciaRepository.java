// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.domain.model.Dependencia;


/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface DependenciaRepository {
    
    void create(Dependencia dependencia);

    Dependencia update(Dependencia dependencia);

    void delete(Dependencia dependencia);

    public Dependencia findBy(String abreviacion);
}
