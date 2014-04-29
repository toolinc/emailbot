// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.application.command.RegisterEmailCommand;
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
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@RunAsClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterEmailResourceTest extends PersistenceTest {
    private static final String RESOURCE_PREFIX = JaxRsActivator.class
            .getAnnotation(ApplicationPath.class).value().substring(1) + "/register/";

    private final String mediaType = MediaType.APPLICATION_JSON;
    @ArquillianResource
    private URL deploymentUrl;
    private Client client;
    private WebTarget target;

    @Before
    public void initClient() {
        client = ClientBuilder.newClient().register(GsonProvider.class);
        target = this.client.target(deploymentUrl.toString() + RESOURCE_PREFIX);
    }

    @Test
    public void shouldRegisterWorker() throws Exception {
        RegisterEmailCommand command = new RegisterEmailCommand("Jovani", "Martinez", "Rico",
                new GregorianCalendar(1990, 07, 26).getTime(), "H46", "306204614",
                "DGTIC", "Direccion General de Telecomunicaciones y Computo",
                "jovanimtzrico@gmail.com", "571201109", "jovmtzrico");

        Response response = target.path("worker")
                .request()
                .post(Entity.entity(command, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
    }
}
