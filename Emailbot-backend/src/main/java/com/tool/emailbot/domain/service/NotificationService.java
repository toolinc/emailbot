// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.service;

import com.tool.emailbot.common.AssertionConcern;

/**
 * The aim of the service is to send and email.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class NotificationService extends AssertionConcern {

    //TODO(jovanimtzrico): We need to specify a different argument as part of this call.
    public boolean sendEmail(String email) {
        return true;
    }
}
