// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.google.common.annotations.VisibleForTesting;
import com.tool.emailbot.application.command.WorkerInformationCommand;
import com.tool.emailbot.infraestructure.resource.GsonProvider;
import com.tool.emailbot.infraestructure.resource.JaxRsActivator;

import java.net.URL;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Invokes the Worker Web Service to retrieve the Worker Information.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class WorkerInfoResource {

    private static final String RESOURCE_PREFIX = JaxRsActivator.class
            .getAnnotation(ApplicationPath.class).value().substring(1) + "/changeme/";
    @VisibleForTesting
    public static URL deploymentUrl;
    private final String mediaType = MediaType.APPLICATION_JSON;
    private Client client;
    private WebTarget target;

    public WorkerInfoResource() {
        client = ClientBuilder.newClient().register(GsonProvider.class);
        target = this.client.target(deploymentUrl.toString() + RESOURCE_PREFIX);
    }

    /**
     * Retrieves the worker information from the external service.
     *
     * @param command specifies the Worker Number from which its information is required.
     * @return the worker information
     */
    public WorkerInformationCommand retrieveWorkerInfo(WorkerInformationCommand command) {
        Response response = target.path("changeme").request()
                .post(Entity.entity(command, mediaType), Response.class);
        if (response.getStatus() == 200) {
            command = response.readEntity(WorkerInformationCommand.class);
        } else {
            command = null;
        }
        return command;
    }
}
