// Copyright 2014 Tool Inc.
package com.tool.emailbot.adapter.jpa;

import com.tool.emailbot.common.adapter.jpa.repository.QueryHelper;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.TrabajadorRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaTrabajadorRepository implements TrabajadorRepository {

    private final Repository<Trabajador> repository;

    @Inject
    public JpaTrabajadorRepository(Repository<Trabajador> repository) {
	this.repository = repository;
    }

    @Override
    public void create(Trabajador trabajador) {
	repository.create(trabajador);
    }

    @Override
    public Trabajador update(Trabajador trabajador) {
	return repository.update(trabajador);
    }

    @Override
    public void delete(Trabajador trabajador) {
	repository.delete(trabajador);
    }

    @Override
    public Trabajador findBy(String numeroTrabajador) {
	Trabajador t = null;
	QueryHelper<Trabajador, Trabajador> qh = repository.newQueryHelper();
	try {
	    qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(numeroTrabajador),
						      numeroTrabajador));
	    t = qh.getSingleResult();
	} catch (NoResultException e) {
	}
	return t;
    }
}
