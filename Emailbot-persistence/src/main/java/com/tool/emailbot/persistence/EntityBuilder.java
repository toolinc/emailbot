// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence;

import java.io.Serializable;

/**
 * Specifies the contract of the builder pattern for an entity.
 *
 * @param <T> The entity which the builder will create a new correct instance.
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface EntityBuilder<T extends Entidad> extends Serializable{
    T build();
}
