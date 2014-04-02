// Copyright 2014 Tool Inc.

package com.tool.emailbot.common.domain.event;

/**
 * Specifies the contract for a {@link com.tool.emailbot.common.domain.event.DomainEvent} subscriber.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 * @param <T> specifies the type of events that the subscriber handles
 */
public interface EventSubscriber<T extends DomainEvent> {

    /**
     * This call back will be invoke upon reception of a given
     * {@link com.tool.emailbot.common.domain.event.DomainEvent} message.
     *
     * @param domainEvent the type of message that will be handle by the subscriber
     */
    void handle(final T domainEvent);
}
