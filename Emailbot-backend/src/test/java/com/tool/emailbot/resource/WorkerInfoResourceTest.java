// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.PersistenceTest;
import com.tool.emailbot.application.command.WorkerInformationCommand;
import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test for {@link com.tool.emailbot.resource.WorkerInfoResource}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@RunAsClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkerInfoResourceTest extends PersistenceTest {

    @ArquillianResource
    private URL deploymentUrl;
    private WorkerInfoResource resource;

    @Before
    public void init() {
        WorkerInfoResource.deploymentUrl = deploymentUrl;
	this.resource = new WorkerInfoResource();
    }

    @Test
    public void testRetrieveWorkerInfo() throws Exception {
	WorkerInformationCommand command = new WorkerInformationCommand(
                "306204614", true, "DGTIC", 
                "Direccion General de Telecomunicaciones e Inforatica");
	WorkerInformationCommand informationCommand = resource
                .retrieveWorkerInfo(command);
    }
}
