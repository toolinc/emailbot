// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.common.domain.repository;

import com.tool.emailbot.common.adapter.jpa.repository.QueryHelper;
import com.tool.emailbot.common.domain.model.DomainObject;

import java.util.UUID;

import javax.persistence.EntityManager;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @param <T> Specifies the entity of the Repository.
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface Repository<T extends DomainObject> {

    void create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(UUID key);

    QueryHelper<T, T> newQueryHelper();

    EntityManager getEntityManager();
}
