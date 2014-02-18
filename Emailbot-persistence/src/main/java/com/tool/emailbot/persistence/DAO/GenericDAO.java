// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.DAO;

import java.io.Serializable;

/**
 * This interface represents a Person Data Access Object.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface GenericDAO<T, K extends Serializable> {
    T create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(K key);
}
