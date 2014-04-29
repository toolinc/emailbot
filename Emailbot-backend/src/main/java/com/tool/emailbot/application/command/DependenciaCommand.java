// Copyright 2014 University of Detroit Mercy.

package com.tool.emailbot.application.command;

/**
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class DependenciaCommand implements Command{
    private String abreviacion;
    private String nombre;

    public DependenciaCommand(String abreviacion, String nombre) {
	setNombre(nombre);
	setAbreviacion(abreviacion);
    }

    public String getAbreviacion() {
	return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
	this.abreviacion = abreviacion;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
}
