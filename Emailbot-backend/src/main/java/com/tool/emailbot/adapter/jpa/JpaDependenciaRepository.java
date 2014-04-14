// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.jpa;

import com.tool.emailbot.common.adapter.jpa.repository.QueryHelper;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.Dependencia_;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.repository.DependenciaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaDependenciaRepository implements DependenciaRepository{
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Repository<Dependencia> repository;
    
    @Inject
    public JpaDependenciaRepository(Repository<Dependencia> repository){
	this.repository=repository;
    }

    @Override
    public void create(Dependencia dependencia) {
	repository.create(dependencia);
    }

    @Override
    public Dependencia update(Dependencia dependencia) {
	return repository.update(dependencia);
	
    }

    @Override
    public void delete(Dependencia dependencia) {
	repository.delete(dependencia);
    }

    @Override
    public Dependencia findBy(String abreviacion) {
	Dependencia dependencia = null;
	QueryHelper<Dependencia, Dependencia> qh = repository.newQueryHelper();
	qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(Dependencia_.abreviacion), abreviacion));
	try {
            dependencia = qh.getSingleResult();
        } catch (NoResultException | NonUniqueResultException exc) {
            logger.warn(exc.getMessage());
        }
	return dependencia;
    }
    
}
