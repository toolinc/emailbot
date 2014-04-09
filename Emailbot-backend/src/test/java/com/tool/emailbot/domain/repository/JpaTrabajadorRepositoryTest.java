/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.domain.repository;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Trabajador;

import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 *
 * @author edgar
 */
public class JpaTrabajadorRepositoryTest extends PersistenceTest {

    @Inject
    private EntityManager entityManager;
    @Inject
    private UserTransaction tx;
    @Inject
    private Repository<Dependencia> daoDependencia;
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
