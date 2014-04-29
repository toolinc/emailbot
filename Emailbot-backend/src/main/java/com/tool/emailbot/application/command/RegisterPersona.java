//Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

import java.util.Date;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 * 
 */
public class RegisterPersona {
    private PersonaCommand personaCommand;

    public RegisterPersona(String nombrePersona, String apellidoPaterno, String apellidoMaterno,
	    Date fechaNacimiento, String homoclave, String rfc,
	    InformacionContactoCommand informacionContacto) {
	
	setPersonaCommand(new PersonaCommand(nombrePersona, apellidoPaterno, apellidoMaterno,
					     fechaNacimiento, homoclave, rfc, informacionContacto));
    }

    public PersonaCommand getPersonaCommand() {
	return personaCommand;
    }

    public void setPersonaCommand(
	    PersonaCommand personaCommand) {
	this.personaCommand = personaCommand;
    }

    
    
}
