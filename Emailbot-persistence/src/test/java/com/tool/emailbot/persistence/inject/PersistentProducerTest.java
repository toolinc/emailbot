// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.inject;

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
public class PersistentProducerTest {

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
}