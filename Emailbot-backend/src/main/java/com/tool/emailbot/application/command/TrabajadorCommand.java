//Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class TrabajadorCommand implements Command{
    private PersonaCommand persona;
    private DependenciaCommand dependencia;
    private String numeroTrabajador;
    private SituacionLaboralCommand situcionLaboral;
    private boolean director;

    public TrabajadorCommand(PersonaCommand persona, DependenciaCommand dependencia,
	    String numeroTrabajador, SituacionLaboralCommand situcionLaboral, boolean director) {
	setPersona(persona);
	setDependencia(dependencia);
	setNumeroTrabajador(numeroTrabajador);
	setSitucionLaboral(situcionLaboral);
	setDirector(director);
    }

    public PersonaCommand getPersona() {
	return persona;
    }

    public void setPersona(PersonaCommand persona) {
	this.persona = persona;
    }

    public DependenciaCommand getDependencia() {
	return dependencia;
    }

    public void setDependencia(
	    DependenciaCommand dependencia) {
	this.dependencia = dependencia;
    }

    public String getNumeroTrabajador() {
	return numeroTrabajador;
    }

    public void setNumeroTrabajador(String numeroTrabajador) {
	this.numeroTrabajador = numeroTrabajador;
    }

    public SituacionLaboralCommand getSitucionLaboral() {
	return situcionLaboral;
    }

    public void setSitucionLaboral(
	    SituacionLaboralCommand situcionLaboral) {
	this.situcionLaboral = situcionLaboral;
    }

    public boolean isDirector() {
	return director;
    }

    public void setDirector(boolean director) {
	this.director = director;
    }
    
    
    
}
