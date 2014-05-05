// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.service;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.DependenciaRepository;
import com.tool.emailbot.domain.repository.TrabajadorRepository;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.domain.service}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateWorkerInfoServiceTest extends PersistenceTest {

    private final Trabajador.Builder builderTrabajador = Trabajador.Builder.newBuilder();
    private final InformacionContacto.Builder iBuider = InformacionContacto.Builder.newBuilder();
    private final Persona.Builder buiderPersona = Persona.Builder.newBuilder();
    private final String workerNumber = "303204614";
    private Dependencia dependencia;
    private Trabajador trabajador;
    @Inject UserTransaction tx;
    @Inject TrabajadorRepository trabajadorRepository;
    @Inject DependenciaRepository dependenciaRepository;
    @Inject ValidateWorkerInfoService service;

    @Before
    public void init() throws Exception {
        dependencia = new Dependencia.Builder().build();
        iBuider.setEmail("jovanimtzrico@gmail.com");
        buiderPersona.setNombre("Jovani").setApellidoMaterno("Rico")
                .setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26)
                .setHomoclave("ohm").setInformacionContacto(iBuider);
        trabajador = builderTrabajador
                .setDependencia(dependencia)
                .setDirector(true).setNumeroTrabajador(workerNumber)
                .setPersona(buiderPersona).build();

        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(dependencia);
        trabajadorRepository.create(trabajador);
        tx.commit();
    }

    @Test
    public void testValidateWorkerInformation() throws Exception {
        service.validateWorkerInformation(trabajador);
    }
}
