// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import com.tool.emailbot.PersistenceTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;
import javax.transaction.RollbackException;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.persistence.domain.model.Trabajador}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DependenciaTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;

    private final Dependencia.Builder builder = Dependencia.Builder.newBuilder();

    @Test
    public void shouldCreateDependencia() throws Exception {
        Dependencia dependencia = builder.setAbreviacion("nueva")
                .setNombre("Nueva Dependencia")
                .build();
        tx.begin();
        em.joinTransaction();
        em.persist(dependencia);
        tx.commit();
    }

    @Test(expected = RollbackException.class)
    public void shouldFailDueToUniqueViolation() throws Exception {
        Dependencia dependencia = builder.setAbreviacion("nueva")
                .setNombre("Nueva Dependencia NNN")
                .build();
        tx.begin();
        em.joinTransaction();
        em.persist(dependencia);
        tx.commit();
    }
}
