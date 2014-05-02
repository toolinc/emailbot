// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.domain.model.Dependencia;

import org.junit.Assert;
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
    @Inject private DependenciaRepository dependenciaRepository;
    private final Dependencia.Builder builder = Dependencia.Builder.newBuilder();

    @Test
    public void shouldPersistDependencia() throws Exception {
        Dependencia dependencia = builder.setAbreviacion("daoDependencia")
                .setNombre("Nueva Dependencia DAO")
                .build();
        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        tx.commit();
    }

    @Test
    public void shouldPersistDefaultDependencia() throws Exception {
        String abreviacion = "UNAM";
        Dependencia dependencia = builder.build();
        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        tx.commit();
        Assert.assertEquals(abreviacion, dependencia.getAbreviacion());
    }

    @Test
    public void shouldFindDependencia() throws Exception {
        String abreviacion = "ENP";
        Dependencia d = null;
        Dependencia dependencia = builder.setAbreviacion("ENP").setNombre("Escuela Nacional").build();
        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        d = dependenciaRepository.findBy(abreviacion);
        tx.commit();
        Assert.assertNotNull(d);
    }
}
