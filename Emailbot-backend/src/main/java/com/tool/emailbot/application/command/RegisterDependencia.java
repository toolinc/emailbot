// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.application.command;

/**
 * Command that specifies that a new dependencia user should be created.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterDependencia {
    private DependenciaCommand dependenciaCommand;

    public RegisterDependencia(String abreviacion, String nombre) {
	setDependenciaCommand(new DependenciaCommand(abreviacion, nombre) );
    }

    public DependenciaCommand getDependenciaCommand() {
	return dependenciaCommand;
    }

    public void setDependenciaCommand(
	    DependenciaCommand dependenciaCommand) {
	this.dependenciaCommand = dependenciaCommand;
    }
}
