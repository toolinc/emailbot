// Copyright 2014 Tool Inc.

package com.tool.emailbot.application;

import com.tool.emailbot.application.command.RegisterEmailCommand;
import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.domain.EmailbotException;
import com.tool.emailbot.domain.service.RegisterEmailService;

import javax.inject.Inject;

/**
 * Provides all the features related to email robot.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class EmailApplicationService extends AssertionConcern {
    
    private final RegisterEmailService registerEmailService;

    @Inject
    public EmailApplicationService(RegisterEmailService registerEmailService) {
        assertArgumentNotNull(registerEmailService, "The Register Email Service is null.");
        this.registerEmailService = registerEmailService;
    }

    /**
     * Registers a new {@link com.tool.emailbot.application.command.RegisterEmailCommand} on the system.
     *
     * @param command the request that will be stored.
     * @throws EmailbotException if a request cannot be created
     */
    public void registerEmailRequest(RegisterEmailCommand command) throws EmailbotException {
    }
}
