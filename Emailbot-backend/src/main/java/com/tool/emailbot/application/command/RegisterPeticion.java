// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.application.command;

/**
 * Command that specifies that a new peticion user should be created.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterPeticion {
    private PeticionComammand peticionComammand;

    public RegisterPeticion(TrabajadorCommand tc,EstatusCommand ec,String email, String username, AprovacionCommand ac) {
	setPeticionComammand(new PeticionComammand(tc,ec,email,username));
    }

    public PeticionComammand getPeticionComammand() {
	return peticionComammand;
    }

    public void setPeticionComammand(
	    PeticionComammand peticionComammand) {
	this.peticionComammand = peticionComammand;
    }
    
    
    
}
