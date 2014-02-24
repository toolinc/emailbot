// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.dao;

import com.tool.emailbot.persistence.Entidad;

import java.util.UUID;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @param <T> Specifies the entity of the DAO.
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface DAO<T extends Entidad> {

    //TODO(jovani): add proper java doc for each method.
    T create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(UUID key);
}
