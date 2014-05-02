// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.jpa;

import static com.tool.emailbot.domain.model.Peticion_.trabajador;

import com.tool.emailbot.common.adapter.jpa.repository.QueryHelper;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Peticion_;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.model.Trabajador_;
import com.tool.emailbot.domain.repository.PeticionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaPeticionRepository implements PeticionRepository {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Repository<Peticion> repository;

    @Inject
    public JpaPeticionRepository(Repository<Peticion> repository) {
        this.repository = repository;
    }

    @Override
    public void create(Peticion peticion) {
        repository.create(peticion);
    }

    @Override
    public Peticion update(Peticion peticion) {
        return repository.update(peticion);
    }

    @Override
    public void delete(Peticion peticion) {
        repository.delete(peticion);
    }

    @Override
    public Peticion findBy(String numeroTrabajador) {
        Peticion peticion = null;
        QueryHelper<Peticion, Peticion> qh = repository.newQueryHelper();
        qh.getRoot().fetch(Peticion_.trabajador);
        qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(Peticion_.trabajador)
                .get(Trabajador_.numeroTrabajador), numeroTrabajador));
        try {
            peticion = qh.getSingleResult();
        } catch (NoResultException | NonUniqueResultException exc) {
            logger.warn(exc.getMessage());
        }
        return peticion;
    }

    @Override
    public List<Peticion> find(Estatus estatus, int size) {
        List<Peticion> peticion = null;
        QueryHelper<Peticion, Peticion> qh = repository.newQueryHelper();
        qh.getRoot().fetch(Peticion_.trabajador).fetch(Trabajador_.dependencia);
        qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(Peticion_.estatus), estatus));
        try {
            peticion = qh.getResultList(0, size);
        } catch (NoResultException | NonUniqueResultException exc) {
            logger.warn(exc.getMessage());
        }
        return peticion;
    }
}
