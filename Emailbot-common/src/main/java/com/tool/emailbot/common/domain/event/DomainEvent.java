// Copyright 2014 Tool Inc.

package com.tool.emailbot.common.domain.event;

import java.util.Date;

/**
 * Represents an event of a {@link com.tool.emailbot.common.domain.model.DomainObject}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public interface DomainEvent {

    /**
     * Provides the event version.
     *
     * @return the version number
     */
    public int getEventVersion();

    /**
     * Provides the date in which the event was produced.
     *
     * @return the date of the event.
     */
    public Date getOccurredOn();
}
