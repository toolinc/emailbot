// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.dao.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.dao.DAO;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * This class represents a Person Data Access Object implementation.
 *
 * @param <T> Specifies the entity of the DAO.
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class GenericDAOImpl<T extends Entidad> implements DAO<T> {

    private final EntityManager em;

    @Inject
    public GenericDAOImpl(EntityManager entityManager) {
        this.em = checkNotNull(entityManager);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public T findById(UUID key) {
        return em.find(getEntityClass(), key);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }
}
