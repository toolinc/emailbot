// Copyright 2014 Tool Inc.

package com.tool.emailbot.infrastructure.cdi;

import com.tool.emailbot.common.adapter.jpa.repository.JpaRepository;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.Trabajador;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Specifies the injection producer of the persistence module.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@ApplicationScoped
public class EmailbotModuleTest {

    private static final String PERSISTENCE_UNIT = "emailbotPUTest";

    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    private EntityManager entityManager;

    @Produces
    public EntityManager produceEntityManager() {
        return entityManager;
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Produces
    public Repository<Dependencia> produceDependenciaRepository(EntityManager entityManager) {
        return new JpaRepository<Dependencia>(entityManager) {
        };
    }

    @Produces
    public Repository<Trabajador> produceTrabajadorRepository(EntityManager entityManager) {
        return new JpaRepository<Trabajador>(entityManager) {
        };
    }
}
