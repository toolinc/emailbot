// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.repository;

import com.tool.emailbot.adapter.jpa.JpaTrabajadorRepository;
import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Peticion;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.service.RegisterEmailService;

import org.junit.Test;

import javax.inject.Inject;

/**
 * Test for the class {@link com.tool.emailbot.domain.repository}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com.tool.emailbot.domain.model.Trabajadorcom)
 */
public class RegisterEmailServiceTest {
    private RegisterEmailService service;
    @Inject
    private TrabajadorRepository daoTrabajador;
    @Inject
    private Repository<Peticion> daoPeticion;
    private final Trabajador.Builder tbuilder = Trabajador.Builder.newBuilder();
    private final Peticion.Builder pBuilder = Peticion.Builder.newBuilder();
    
    
    @Test
    public void shouldRegisterEmail(){
	
	service = new RegisterEmailService(null, null);
    }
}
