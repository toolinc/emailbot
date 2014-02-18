// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.dao.impl;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.dao.GenericDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class represents a Person Data Access Object implementation.
 *
 * @param <T> Specifies the entity of the DAO.
 * @param <K> Specifies the {@link javax.persistence.Id} of the entity.
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class GenericDAOImpl<T extends Entidad, K extends Serializable> implements GenericDAO<T, K> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<T> classType;

    public GenericDAOImpl() {
        super();
        this.classType = (Class<T>) (getParameterClass(getClass(), 0));
    }

    @Override
    public T create(T entity) {
        this.em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return this.em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.em.remove(entity);
    }

    @Override
    public T findById(K key) {
        return this.em.find(classType, key);
    }

    private static Class<?> getParameterClass(Class<?> clazz, int index) {
        return (Class<?>) (((ParameterizedType) clazz.getGenericSuperclass()).
                getActualTypeArguments()[index]);
    }
}
