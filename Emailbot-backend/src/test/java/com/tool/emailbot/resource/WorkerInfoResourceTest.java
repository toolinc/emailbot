// Copyright 2014 Tool Inc.

package com.tool.emailbot.resource;

import com.tool.emailbot.application.command.WorkerInformationCommand;

import org.jboss.arquillian.container.test.api.RunAsClient;
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
public class WorkerInfoResourceTest {


    private final WorkerInfoResource resource;
    private WorkerInformationCommand informationCommand;


    public WorkerInfoResourceTest() {
	this.resource = new WorkerInfoResource();
    }

    @Test
    public void testRetrieveWorkerInfo() throws Exception {
	WorkerInformationCommand command
		= new WorkerInformationCommand("306204614", true, "DGTIC",
					       "Direccion General de Telecomunicaciones e Inforatica");
	informationCommand = resource.retrieveWorkerInfo(command);
	
    }
}
