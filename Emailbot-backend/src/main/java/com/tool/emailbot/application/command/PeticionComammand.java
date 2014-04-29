//Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

/**
 * Command represents a peticion
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class PeticionComammand implements Command{
    private TrabajadorCommand trabajador;
    private EstatusCommand estatus;
    private String email;
    private String username;


    public PeticionComammand(TrabajadorCommand trabajador, EstatusCommand estatus, String email, String username) {
	setTrabajador(trabajador);
	setEstatus(estatus);
	setEmail(email);
	setUsername(username);
	
    }

    PeticionComammand(TrabajadorCommand tc, EstatusCommand ec, String email, String username,
	    AprovacionCommand ac) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TrabajadorCommand getTrabajador() {
	return trabajador;
    }

    public void setTrabajador(
	    TrabajadorCommand trabajador) {
	this.trabajador = trabajador;
    }

    public EstatusCommand getEstatus() {
	return estatus;
    }

    public void setEstatus(EstatusCommand estatus) {
	this.estatus = estatus;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

}
