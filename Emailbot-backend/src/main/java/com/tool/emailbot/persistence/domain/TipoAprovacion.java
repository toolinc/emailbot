// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.domain;

/**
 * This class represents the approval of a {@link com.tool.emailbot.persistence.domain.Peticion}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public enum TipoAprovacion {
    /**
     * A request was approved by a director.
     */
    APROVADA_DIRECTOR,

    /**
     * A request was denied by a director.
     */
    RECHAZADA_DIRECTOR,

    /**
     * A request was approved by the system.
     */
    APROVADA_SISTEMA,

    /**
     * A request was denied by the system.
     */
    RECHAZADA_SISTEMA
}
