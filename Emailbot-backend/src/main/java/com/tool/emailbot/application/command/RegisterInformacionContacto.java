// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.application.command;

/**
 * Command that specifies that a new informacion contacto user should be created.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterInformacionContacto {
    private InformacionContactoCommand informacionContactoCommand;

    public RegisterInformacionContacto(String email, String telefono) {
	setInformacionContactoCommand(new InformacionContactoCommand(email, telefono));
    }

    public InformacionContactoCommand getInformacionContactoCommand() {
	return informacionContactoCommand;
    }

    public void setInformacionContactoCommand(
	    InformacionContactoCommand informacionContactoCommand) {
	this.informacionContactoCommand = informacionContactoCommand;
    }
}
