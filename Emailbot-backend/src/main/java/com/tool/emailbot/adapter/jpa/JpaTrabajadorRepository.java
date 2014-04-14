// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.jpa;

import com.tool.emailbot.common.adapter.jpa.repository.QueryHelper;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.model.Trabajador_;
import com.tool.emailbot.domain.repository.TrabajadorRepository;

import org.slf4j.Logger;//cascade peticion 
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaTrabajadorRepository implements TrabajadorRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());
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
        qh.getQuery().where(qh.getBuilder().equal(
                qh.getRoot().get(Trabajador_.numeroTrabajador), numeroTrabajador));
        try {
            t = qh.getSingleResult();
        } catch (NoResultException | NonUniqueResultException exc) {
            logger.warn(exc.getMessage());
        }
        return t;
    }
}
