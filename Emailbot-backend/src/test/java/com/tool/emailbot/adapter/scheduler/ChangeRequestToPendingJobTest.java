// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.scheduler;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.adapter.jpa.JpaPeticionRepository;
import com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository;
import com.tool.emailbot.adapter.scheduler.task.ChangeRequestToPendingTask;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.Estatus;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link ChangeRequestToPendingJob}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class ChangeRequestToPendingJobTest extends PersistenceTest {

   
    private final Dependencia.Builder bDependencia = Dependencia.Builder.newBuilder();
    private final InformacionContacto.Builder bInfo = InformacionContacto.Builder.newBuilder();
    private final Persona.Builder bPersona = Persona.Builder.newBuilder();
    private final Trabajador.Builder bTrabajador = Trabajador.Builder.newBuilder();
    private final Peticion.Builder bPeticion = Peticion.Builder.newBuilder();
    private Peticion peticion;
    @Inject private Repository<Dependencia> daoDependencia;
    @Inject private JpaTrabajadorRepository daoTrabajador;
    @Inject private JpaPeticionRepository daoPeticion;
    @Inject private UserTransaction tx;
    @Inject private ChangeRequestToPendingTask instance;
    @Inject private ChangeRequestToPendingJob pendingJob;

    @Test
    public void shouldPersistPeticion() throws Exception {
        Dependencia dependencia = bDependencia.setAbreviacion("DGTIC")
                .setNombre("Direccion General")
                .build();
        bInfo.setEmail("jovanimtzrico@gmail.com");
        bPersona.setNombre("Jovani")
                .setApellidoMaterno("Rico")
                .setApellidoPaterno("Martinez")
                .setFechaNacimiento(1990, 07, 26)
                .setHomoclave("ohm")
                .setInformacionContacto(bInfo);
        Trabajador trabajador = bTrabajador.setDependencia(dependencia)
                .setDirector(true)
                .setNumeroTrabajador("303204614")
                .setPersona(bPersona)
                .build();
        peticion = bPeticion.setTrabajador(trabajador)
                .setEmail("jovanimtzrico@gmail.com")
                .setUsername("Jovani")
                .build();
        tx.begin();
        em.joinTransaction();
        daoDependencia.create(dependencia);
        daoTrabajador.create(trabajador);
        daoPeticion.create(peticion);
        tx.commit();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        peticion = daoPeticion.findBy(trabajador.getNumeroTrabajador());
        assertEquals(Estatus.PENDIENTE, peticion.getEstatus());
    }
}
