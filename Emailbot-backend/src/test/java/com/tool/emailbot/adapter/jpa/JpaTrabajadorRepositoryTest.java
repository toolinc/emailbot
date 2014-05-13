// Copyright 2014 Tool Inc.

package com.tool.emailbot.adapter.jpa;

import static org.junit.Assert.assertEquals;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Dependencia;
import com.tool.emailbot.domain.model.InformacionContacto;
import com.tool.emailbot.domain.model.Persona;
import com.tool.emailbot.domain.model.Trabajador;

import junit.framework.Assert;
import org.junit.Test;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
//TODO(jovanimtzrico): Missing the test for the method findBy(Dependencia dependencia)

public class JpaTrabajadorRepositoryTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;
    @Inject
    private Repository<Dependencia> daoDependencia;
    @Inject
    private JpaTrabajadorRepository daoTrabajador;

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

    @Test
    public void shouldFindTrabajadorByDependencia() throws Exception {
        String email = "jovanimtzrico@gmail.com".toUpperCase();
        Dependencia dependencia = buiderDependencia.setAbreviacion("DGTIC").setNombre(
                "Direccion General").build();
        iBuider.setEmail(email);
        buiderPersona.setNombre("Edgar").setApellidoMaterno("Rico").
                setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26).setHomoclave("ohm").
                setInformacionContacto(iBuider);
        Trabajador director = builder.setDependencia(dependencia).setDirector(true).
                setNumeroTrabajador("303204614").setPersona(buiderPersona).build();


        iBuider.setEmail("jovani.martinez.rico@gmail.com");
        buiderPersona.setNombre("Jovani").setApellidoMaterno("Rico").
                setApellidoPaterno("Martinez").setFechaNacimiento(1990, 07, 26).setHomoclave("ohm").
                setInformacionContacto(iBuider);
        Trabajador trabajador = builder.setDependencia(dependencia).setDirector(false).
                setNumeroTrabajador("303204615").setPersona(buiderPersona).build();

        tx.begin();
        em.joinTransaction();
        daoDependencia.create(dependencia);
        daoTrabajador.create(director);
        daoTrabajador.create(trabajador);
        tx.commit();
        assertEquals(daoTrabajador.findBy(dependencia),email);
    }
}
