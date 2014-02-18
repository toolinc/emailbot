// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.PersistenceTest;

import org.junit.Test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.persistence.domain.Trabajador}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
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
}
