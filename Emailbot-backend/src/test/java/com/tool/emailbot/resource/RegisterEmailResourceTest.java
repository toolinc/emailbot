// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.application.command.EstatusCommand;
import com.tool.emailbot.application.command.RegisterDependencia;
import com.tool.emailbot.application.command.RegisterInformacionContacto;
import com.tool.emailbot.application.command.RegisterPersona;
import com.tool.emailbot.application.command.RegisterPeticion;
import com.tool.emailbot.application.command.RegisterTrabajador;
import com.tool.emailbot.application.command.SituacionLaboralCommand;
import com.tool.emailbot.infraestructure.resource.GsonProvider;
import com.tool.emailbot.infraestructure.resource.JaxRsActivator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Test for {@link com.tool.emailbot.resource.RegisterEmailResource}.
 *
 *  @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@RunAsClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterEmailResourceTest extends PersistenceTest {
    private static final String RESOURCE_PREFIX = JaxRsActivator.class
            .getAnnotation(ApplicationPath.class).value().substring(1) + "/register/";

    private final String mediaType = MediaType.APPLICATION_JSON;
    @ArquillianResource private URL deploymentUrl;
    private Client client;
    private WebTarget target;

    @Before
    public void initClient() {
        client = ClientBuilder.newClient().register(GsonProvider.class);
        target = this.client.target(deploymentUrl.toString() + RESOURCE_PREFIX);
    }

    @Test
    public void shouldRegisterPersonUser() throws Exception {
        RegisterDependencia dependencia =
                new RegisterDependencia("DGTIC",
                        "Direccion General de Telecomunicaciones y Computo");
        RegisterInformacionContacto informacionContacto = new RegisterInformacionContacto(
                "Jovanimtzrico@gmail.com", "571201109");
	RegisterPersona persona;
	persona = new RegisterPersona("Jovani", "Martinez", "Rico",
            new GregorianCalendar(1990,07,26).getTime(), "ohm", "jov",
            informacionContacto.getInformacionContactoCommand());
	RegisterTrabajador trabajador = new RegisterTrabajador(persona.getPersonaCommand(),
            dependencia.getDependenciaCommand(), "306204614",
            SituacionLaboralCommand.ACTIVO, true);
	RegisterPeticion peticion = new RegisterPeticion(trabajador.getTrabajadorCommand(),
            EstatusCommand.CREADA, "jovanimtzrico@unam.mx", "Jovani", null);
	Response response = target.path("person")
                .request()
                .post(Entity.entity(peticion, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
    }
}
