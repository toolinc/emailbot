// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.EmailbotException;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Trabajador;

import org.junit.Test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaTrabajadorRepositoryTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;
    @Inject
    private Repository<Dependencia> daoDependencia;
    @Inject
    private Repository<Trabajador> daoTrabajador;
    private final Dependencia.Builder buiderDependencia = Dependencia.Builder.newBuilder();
    private final InformacionContacto.Builder iBuider = InformacionContacto.Builder.newBuilder();
    private final Persona.Builder buiderPersona = Persona.Builder.newBuilder();
    private final Trabajador.Builder builder = Trabajador.Builder.newBuilder();

    @Test
    public void shouldPersistTrabajador() throws Exception {
	Dependencia dependencia = buiderDependencia.setAbreviacion("DGTIC").setNombre(
		"Direccion General").build();
	iBuider.setEmail("jovanimtzrico@gmail.com");
	buiderPersona.setNombre("Jovani").setApellidoMaterno("Rico").
		setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26).setHomoclave("ohm").
		setInformacionContacto(iBuider);
	Trabajador trabajador = builder.setDependencia(dependencia).setDirector(true).
		setNumeroTrabajador("303204614").setPersona(buiderPersona).build();
	tx.begin();
	em.joinTransaction();
	daoDependencia.create(dependencia);
	daoTrabajador.create(trabajador);
	tx.commit();
    }
}
