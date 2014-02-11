// Copyright 2014 Tool Inc.
package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.PersistenceTest;

import org.junit.Test;

/**
 * Test for the class {@link com.tool.emailbot.persistence.domain.Trabajador}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class TrabajadorTest extends PersistenceTest {

    private InformacionContacto.Builder iBuilder = InformacionContacto.Builder.newBuilder();
    private Persona.Builder pBuidelr = Persona.Builder.newBuilder();
    private Trabajador.Builder tbuilder = Trabajador.Builder.newBuilder();
    private Dependencia.Builder dBuilder = Dependencia.Builder.newBuilder();

    @Test
    public void shouldCreateWorker() {
	Dependencia dependencia = dBuilder.setNombre("Direccion General de Todo")
		.setAbreviacion("DGT")
		.build();
	iBuilder.setEmail("pepe.perez.ruiz@gmail.com");
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
	em.persist(dependencia);
	em.persist(trabajador);
	tx.commit();
    }
}
