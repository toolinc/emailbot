//Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public enum TipoAprovacionCommand {
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
