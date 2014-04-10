/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.adapter.jpa;

import com.tool.emailbot.common.adapter.jpa.repository.QueryHelper;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Peticion;
import static com.tool.emailbot.domain.model.Peticion_.trabajador;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.model.Trabajador_;
import com.tool.emailbot.domain.repository.PeticionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.JoinType;

/**
 *
 * @author edgar
 */
public class JpaPeticionRepository implements PeticionRepository{
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
        Trabajador t = null;
        QueryHelper<Peticion, Peticion> qh = repository.newQueryHelper();
        qh.getRoot().fetch(trabajador);
	qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(trabajador), numeroTrabajador));
	return peticion;
    }
}
