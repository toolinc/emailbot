// Copyright 2014 Tool Inc.
package com.tool.emailbot.adapter.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.repository.DependenciaRepository;

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

    private final Dependencia.Builder builder = Dependencia.Builder.newBuilder();
    @Inject private EntityManager entityManager;
    @Inject private UserTransaction tx;
    @Inject private DependenciaRepository dependenciaRepository;

    @Test
    public void shouldPersistDependencia() throws Exception {
        String abreviacion = "daoDependencia".toUpperCase();
        String nombre = "Nueva Dependencia DAO".toUpperCase();
        Dependencia dependencia = builder.setAbreviacion(abreviacion).setNombre(nombre).build();
        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        tx.commit();
        assertNotNull(dependencia.getId());
        assertEquals(abreviacion, dependencia.getAbreviacion());
        assertEquals(nombre, dependencia.getNombre());
    }

    @Test
    public void shouldPersistDefaultDependencia() throws Exception {
        String abreviacion = "UNAM";
        Dependencia dependencia = builder.build();
        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        tx.commit();
        assertNotNull(dependencia.getId());
        assertEquals(abreviacion, dependencia.getAbreviacion());
    }

    @Test
    public void shouldFindDependencia() throws Exception {
        String abreviacion = "enp".toUpperCase();
        String nombre = "Escuela Nacional".toUpperCase();
        Dependencia d = null;
        Dependencia dependencia = builder.setAbreviacion(abreviacion).setNombre(nombre).build();
        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        d = dependenciaRepository.findBy(abreviacion);
        tx.commit();
        assertNotNull(d);
        assertEquals(dependencia, d);
        assertEquals(abreviacion, d.getAbreviacion());
        assertEquals(nombre, d.getNombre());
    }
}
