// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.common.domain.event;

import com.tool.emailbot.common.AssertionConcern;

import java.util.Collection;

/**
 * Decorator of the {@link com.tool.emailbot.common.domain.event.EventPublisher}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
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
