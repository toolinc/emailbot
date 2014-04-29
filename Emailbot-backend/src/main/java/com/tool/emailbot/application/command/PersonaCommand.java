// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.application.command;

import java.util.Date;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class PersonaCommand implements Command{
    private String nombrePersona;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String homoclave;
    private String rfc;
    private InformacionContactoCommand informacionContacto;

    public PersonaCommand(String nombrePersona, String apellidoPaterno, String apellidoMaterno,
	    Date fechaNacimiento, String homoclave, String rfc,
	    InformacionContactoCommand informacionContacto) {
	setNombrePersona(nombrePersona);
	setApellidoPaterno(apellidoPaterno);
	setApellidoMaterno(apellidoMaterno);
	setFechaNacimiento(fechaNacimiento);
	setHomoclave(homoclave);
	setRfc(rfc);
    }

    public String getNombrePersona() {
	return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
	this.nombrePersona = nombrePersona;
    }

    public String getApellidoPaterno() {
	return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
	return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
	this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
	return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }

    public String getHomoclave() {
	return homoclave;
    }

    public void setHomoclave(String homoclave) {
	this.homoclave = homoclave;
    }

    public String getRfc() {
	return rfc;
    }

    public void setRfc(String rfc) {
	this.rfc = rfc;
    }

    public InformacionContactoCommand getInformacionContacto() {
	return informacionContacto;
    }

    public void setInformacionContacto(
	    InformacionContactoCommand informacionContacto) {
	this.informacionContacto = informacionContacto;
    }
    
    
    
    
}
