// Copyright 2014 Tool Inc. 
package com.tool.emailbot.persistence;

import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * This class represents a persistence entity. Such entity is {@link Serializable} and also can be
 * activated or deactivated in the persistence storage. In order to guarantee consistency a field
 * stores the optimistic locking of the entity.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@MappedSuperclass
public abstract class Entidad implements Serializable {

    protected static final String EMAIL_REGEX = "^[a-zA-Z][\\w]+(\\.[_\\w-]+)*@[\\w-]+(\\.[\\w]+)*(\\.[A-Za-z]{2,})$";
    protected static final String NULL_REGEX = "^$";
    protected static final String USER_NAME_REGEX = "^[a-zA-Z][\\w]+(\\.[_\\w-]+)*$";

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    public abstract UUID getId();

    public abstract void setId(@NotNull UUID id);

    public boolean isActivo() {
	return activo;
    }

    public void setActivo(boolean activo) {
	this.activo = activo;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 79 * hash + Objects.hashCode(getId());
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Entidad other = (Entidad) obj;
	return Objects.equals(getId(), other.getId());
    }

    protected Date newDate(Date time) {
	Preconditions.checkNotNull(time, "Date should not be [null].");
	return newDate(time.getTime());
    }

    protected Date newDate(long time) {
	return new Date(time);
    }

    protected static Date newDate(int year, int month, int dayOfMonth) {
	GregorianCalendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
	return calendar.getTime();
    }

    public int getVersion() {
	return version;
    }
}
