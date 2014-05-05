// Copyright 2014 Tool Inc.
package com.tool.emailbot.domain.service;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.adapter.jpa.JpaDependenciaRepository;
import com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.DependenciaRepository;
import com.tool.emailbot.domain.repository.TrabajadorRepository;
import com.tool.emailbot.resource.WorkerInfoResource;

import org.junit.Test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.domain.service}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com.tool.emailbot.domain.model.Trabajadorcom)
 */
public class ValidateWorkerInfoServiceTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;
    @Inject
    TrabajadorRepository trabajadorRepository;
    @Inject
    DependenciaRepository dependenciaRepository;

    private final Trabajador.Builder builderTrabajador = Trabajador.Builder.newBuilder();
    private final InformacionContacto.Builder iBuider = InformacionContacto.Builder.newBuilder();
    private final Persona.Builder buiderPersona = Persona.Builder.newBuilder();
    private ValidateWorkerInfoService service;

    @Test
    public void testValidateWorkerInformation() throws Exception {
        service = new ValidateWorkerInfoService(new WorkerInfoResource(), trabajadorRepository,
                dependenciaRepository);
        Dependencia d = new Dependencia.Builder().build();
        iBuider.setEmail("jovanimtzrico@gmail.com");
        buiderPersona.setNombre("Jovani").setApellidoMaterno("Rico")
                .setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26)
                .setHomoclave("ohm").setInformacionContacto(iBuider);
        Trabajador trabajador = builderTrabajador
                .setDependencia(d)
                .setDirector(true).setNumeroTrabajador("303204614")
                .setPersona(buiderPersona).build();

        tx.begin();
        em.joinTransaction();
        dependenciaRepository.create(d);
        trabajadorRepository.create(trabajador);
        service.validateWorkerInformation(trabajador);
        tx.commit();
    }
}
