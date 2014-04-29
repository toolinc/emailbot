//Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterTrabajador {
    private TrabajadorCommand trabajadorCommand;

    public RegisterTrabajador(PersonaCommand persona, DependenciaCommand dependencia,
	    String numeroTrabajador, SituacionLaboralCommand situcionLaboral, boolean director) {
	setTrabajadorCommand(new TrabajadorCommand(persona, dependencia, numeroTrabajador,
						   situcionLaboral, director));
    }

    public TrabajadorCommand getTrabajadorCommand() {
	return trabajadorCommand;
    }

    public void setTrabajadorCommand(
	    TrabajadorCommand trabajadorCommand) {
	this.trabajadorCommand = trabajadorCommand;
    }
    
    
    
}
