// Copyright 2014 Tool Inc.

package com.tool.emailbot.common.domain.event;

import com.tool.emailbot.common.AssertionConcern;

import java.util.Collection;

/**
 * Decorator of the {@link com.tool.emailbot.common.domain.event.EventPublisher}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public abstract class DomainEventPublisher extends AssertionConcern {

    protected static DomainEventPublisher domainEventPublisher;

    protected abstract EventPublisher eventPublisher();

    public <T extends DomainEvent> void publish(T domainEvent) {
        eventPublisher().publish(domainEvent);
    }

    public <T extends DomainEvent> void publishAll(Collection<T> domainEvents) {
        eventPublisher().publishAll(domainEvents);
    }

    public static DomainEventPublisher instance() {
        return domainEventPublisher;
    }
}
