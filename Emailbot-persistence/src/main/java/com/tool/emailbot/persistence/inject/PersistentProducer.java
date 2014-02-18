// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.inject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Specifies the injection producer of the persistence module.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@ApplicationScoped
public class PersistentProducer {

    private static final String PERSISTENCE_UNIT = "emailbotPU";

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
