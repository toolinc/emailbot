// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.application.command;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class InformacionContactoCommand implements Command{
    private String email;
    private String telefono;

    public InformacionContactoCommand(String email, String telefono) {
	setEmail(email);
	setTelefono(telefono);
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }
}
