// Copyright 2014 Tool Inc.

package com.tool.emailbot.infraestructure.resource;

import com.tool.emailbot.domain.EmailbotException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Provides the appropriate response for
 * {@link com.tool.emailbot.domain.EmailbotException}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Provider
public class EmailbotExceptionMapper implements ExceptionMapper<EmailbotException> {

    @Override
    public Response toResponse(EmailbotException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
