// Copyright 2014 Tool Inc.
package com.tool.emailbot.persistence.DAO.JPA;

import com.tool.emailbot.persistence.DAO.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class represents a Person Data Access Object implementation.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class GenericDAOImpl<T, K extends Serializable> implements GenericDAO<T, K> {

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
