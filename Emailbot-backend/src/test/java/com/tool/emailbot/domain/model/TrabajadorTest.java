// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import com.tool.emailbot.PersistenceTest;

import org.junit.Test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.domain.model.Trabajador}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class TrabajadorTest extends PersistenceTest {

    private InformacionContacto.Builder iBuilder = InformacionContacto.Builder.newBuilder();
    private Persona.Builder pBuidelr = Persona.Builder.newBuilder();
    private Trabajador.Builder tbuilder = Trabajador.Builder.newBuilder();
    private Dependencia.Builder dBuilder = Dependencia.Builder.newBuilder();

    @Inject
    private UserTransaction tx;

    @Test
    public void shouldCreateWorker() throws Exception {
        Dependencia dependencia = dBuilder.setNombre("Direccion General de Todo")
                .setAbreviacion("DGT")
                .build();
        iBuilder.setEmail("pepeperezruiz@gmail.com");
        pBuidelr.setNombre("Jose")
                .setApellidoPaterno("Perez")
                .setApellidoMaterno("Ruiz")
                .setFechaNacimiento(1988, 9, 15)
                .setInformacionContacto(iBuilder);
        Trabajador trabajador = tbuilder.setDependencia(dependencia)
                .setNumeroTrabajador("12Pepe1988")
                .setPersona(pBuidelr)
                .build();
        tx.begin();
        em.joinTransaction();
        em.persist(dependencia);
        em.persist(trabajador);
        tx.commit();
    }
}
