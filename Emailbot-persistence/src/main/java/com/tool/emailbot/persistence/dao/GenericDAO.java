// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.dao;

import com.tool.emailbot.persistence.Entidad;

import java.io.Serializable;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @param <T> Specifies the entity of the DAO.
 * @param <K> Specifies the {@link javax.persistence.Id} of the entity.
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface GenericDAO<T extends Entidad, K extends Serializable> {

    //TODO(jovani): add proper java doc for each method.
    T create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(K key);
}
