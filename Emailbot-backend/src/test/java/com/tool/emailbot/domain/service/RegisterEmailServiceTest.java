// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.service;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.DependenciaRepository;

import org.junit.Test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.domain.repository}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com.tool.emailbot.domain.model.Trabajadorcom)
 */
public class RegisterEmailServiceTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;
    @Inject
    private JpaTrabajadorRepository daoTrabajador;
    @Inject
    private RegisterEmailService registerEmailService;
    @Inject
    private Repository<Dependencia> daoDependencia;
    @Inject
    private DependenciaRepository dependenciaRepository;
    

    private final Peticion.Builder builderPeticion = Peticion.Builder.newBuilder();
    private final Dependencia.Builder buiderDependencia = Dependencia.Builder.newBuilder();
    private final InformacionContacto.Builder iBuider = InformacionContacto.Builder.newBuilder();
    private final Persona.Builder buiderPersona = Persona.Builder.newBuilder();
    private final Trabajador.Builder builderTrabajador = Trabajador.Builder.newBuilder();

    @Test
    public void shouldRegisterEmail() throws Exception {

	iBuider.setEmail("jovanimtzrico@gmail.com");
	buiderPersona.setNombre("Jovani").setApellidoMaterno("Rico").
		setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26).setHomoclave("ohm").
		setInformacionContacto(iBuider);
	Trabajador trabajador = builderTrabajador.setDependencia(new Dependencia.Builder().build()).setDirector(true).
		setNumeroTrabajador("303204614").setPersona(buiderPersona).build();
	Peticion peticion = builderPeticion.setEmail("jovani@unam.mx").setUsername("jovani").
		setTrabajador(trabajador).build();

	tx.begin();
	em.joinTransaction();
	registerEmailService.registerEmailRequest(peticion);
	tx.commit();
    }
}
