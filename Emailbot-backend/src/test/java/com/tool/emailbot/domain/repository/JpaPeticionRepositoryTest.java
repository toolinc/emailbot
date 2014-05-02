/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.domain.repository;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.adapter.jpa.JpaPeticionRepository;
import com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;

import org.junit.Assert;
import org.junit.Test;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class JpaPeticionRepositoryTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;
    @Inject
    private Repository<Dependencia> daoDependencia;
    @Inject
    private JpaTrabajadorRepository daoTrabajador;
    @Inject
    private JpaPeticionRepository daoPeticion;

    private final Dependencia.Builder buiderDependencia = Dependencia.Builder.newBuilder();
    private final InformacionContacto.Builder iBuider = InformacionContacto.Builder.newBuilder();
    private final Persona.Builder buiderPersona = Persona.Builder.newBuilder();
    private final Trabajador.Builder builder = Trabajador.Builder.newBuilder();
    private final Peticion.Builder pBuilder = Peticion.Builder.newBuilder();

    //@Test
    public void shouldPersistPeticion() throws Exception {
	Dependencia dependencia = buiderDependencia.setAbreviacion("DGTIC")
		.setNombre("Direccion General").build();
	iBuider.setEmail("jovanimtzrico@gmail.com");
	buiderPersona.setNombre("Jovani").setApellidoMaterno("Rico")
		.setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26)
		.setHomoclave("ohm")
		.setInformacionContacto(iBuider);
	Trabajador trabajador = builder.setDependencia(dependencia).setDirector(true)
		.setNumeroTrabajador("303204614").setPersona(buiderPersona).build();
	Peticion peticion = pBuilder.setTrabajador(trabajador)
		.setEmail("jovanimtzrico@gmail.com").setUsername("Jovani").build();
	tx.begin();
	em.joinTransaction();
	daoDependencia.create(dependencia);
	daoTrabajador.create(trabajador);
	daoPeticion.create(peticion);
	tx.commit();
    }

    //@Test
    public void shouldFindPeticionByTrabajador() throws Exception {
	Peticion p = null;
	Dependencia dependencia = buiderDependencia.setAbreviacion("DGSCA")
		.setNombre("Direccion General de Computo Academico").build();
	iBuider.setEmail("jovanimtzrico@gmail.com");
	buiderPersona.setNombre("Edgar").setApellidoMaterno("Rico")
		.setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26)
		.setHomoclave("ohm")
		.setInformacionContacto(iBuider);
	Trabajador trabajador = builder.setDependencia(dependencia).setDirector(true)
		.setNumeroTrabajador("6032046112434").setPersona(buiderPersona).build();
	Peticion peticion = pBuilder.setTrabajador(trabajador)
		.setEmail("edgarmtz@gmail.com").setUsername("Edgar").build();
	tx.begin();
	em.joinTransaction();
	daoDependencia.create(dependencia);
	daoTrabajador.create(trabajador);
	daoPeticion.create(peticion);
	p = daoPeticion.findBy(peticion.getTrabajador().getNumeroTrabajador());
	tx.commit();
	Assert.assertEquals(p, peticion);
    }

    @Test
    public void shouldFindPeticionByEstatus() throws Exception {
	Peticion p = null;
	Dependencia dependencia = buiderDependencia.setAbreviacion("DGIRE")
		.setNombre("Direccion General Informacion").build();
	iBuider.setEmail("oscar@gmail.com");
	buiderPersona.setNombre("Oscar").setApellidoMaterno("Rico")
		.setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26)
		.setHomoclave("ohm")
		.setInformacionContacto(iBuider);
	Trabajador trabajador = builder.setDependencia(dependencia).setDirector(true)
		.setNumeroTrabajador("9023204614").setPersona(buiderPersona).build();
	Peticion peticion = pBuilder.setTrabajador(trabajador)
		.setEmail("oscarmtzrico@gmail.com").setUsername("Oscar").build();
	tx.begin();
	em.joinTransaction();
	daoDependencia.create(dependencia);
	daoTrabajador.create(trabajador);
	daoPeticion.create(peticion);
	p = daoPeticion.find(Estatus.SOLICITUD, 1).get(0);
	tx.commit();
	Assert.assertEquals(p, peticion);
    }
}
