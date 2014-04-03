// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.dao.impl;

import com.tool.emailbot.persistence.PersistenceTest;
import com.tool.emailbot.persistence.dao.DAO;
import com.tool.emailbot.persistence.domain.Dependencia;

import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.persistence.dao.impl.GenericDAOImpl}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class GenericDAOImplTest extends PersistenceTest {

    @Inject private EntityManager entityManager;
    @Inject private UserTransaction tx;
    @Inject private DAO<Dependencia> daoDependencia;
    private final Dependencia.Builder builder = Dependencia.Builder.newBuilder();

    @Test
    public void shouldPersistDependencia() throws Exception {
        Dependencia dependencia = builder.setAbreviacion("daoDependencia")
                .setNombre("Nueva Dependencia DAO")
                .build();
        tx.begin();
        em.joinTransaction();
        daoDependencia.create(dependencia);
        tx.commit();
    }
}
