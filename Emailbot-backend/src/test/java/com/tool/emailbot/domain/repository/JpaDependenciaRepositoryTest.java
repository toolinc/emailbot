// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;

import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.common.domain.repository.Repository}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaDependenciaRepositoryTest extends PersistenceTest {

    @Inject private EntityManager entityManager;
    @Inject private UserTransaction tx;
    @Inject private Repository<Dependencia> daoDependencia;
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
