// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

/**
 * This class represents a Status of the Worker.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public enum SituacionLaboral {

    /**
     * An active worker.
     */
    ACTIVO(true),
    
    /**
     * An inactive worker.
     */
    INACTIVO(false);

    private final boolean active;

    private SituacionLaboral(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
