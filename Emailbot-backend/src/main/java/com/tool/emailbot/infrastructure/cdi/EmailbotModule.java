// Copyright 2014 Tool Inc.

package com.tool.emailbot.infrastructure.cdi;

import com.tool.emailbot.common.adapter.jpa.repository.JpaRepository;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;

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
public class EmailbotModule {

    private static final String PERSISTENCE_UNIT = "emailbotPU";

    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    private EntityManager entityManager;

    @Produces
    public EntityManager produceEntityManager() {
        return entityManager;
    }
    
    @Produces
    public Repository<Dependencia> produceEventRepository(EntityManager entityManager) {
        return new JpaRepository<Dependencia>(entityManager) {
        };
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
