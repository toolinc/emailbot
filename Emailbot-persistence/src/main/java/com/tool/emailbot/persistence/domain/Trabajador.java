// Copyright 2014 Tool Inc. 
package com.tool.emailbot.persistence.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.EntityBuilder;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This class represents a Worker.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Trabajador")
public class Trabajador extends Entidad {

    @NotNull
    @Id
    @Column(name = "idTrabajador")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false, unique = true)
    private Persona persona;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDependencia", nullable = false)
    private Dependencia dependencia;

    @NotNull
    @Column(name = "numeroTrabajador", nullable = false, length = 50)
    private String numeroTrabajador;

    @NotNull
    @Column(name = "situcionLaboral", nullable = false)
    private boolean situcionLaboral;

    @NotNull
    @Column(name = "director", nullable = false)
    private boolean director;

    @OneToOne(mappedBy = "trabajador", fetch = FetchType.LAZY)
    private Peticion peticion;

    @Deprecated
    public Trabajador(){
    }
    
    private Trabajador(Builder builder){
	this.id = builder.id;
	setDependencia(builder.dependecia);
	setPersona(builder.persona);
	setPeticion(builder.peticion);
	setDirector(builder.director);
	setNumeroTrabajador(builder.numeroTrabajador);
	setSitucionLaboral(builder.situcionLaboral);
    }
	    
    @Override
    public UUID getId() {
	return id;
    }

    @Override
    public void setId(UUID id) {
	this.id = id;
    }

    public Persona getPersona() {
	return persona;
    }

    public void setPersona(Persona persona) {
	this.persona = persona;
    }

    public Dependencia getDependencia() {
	return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
	this.dependencia = dependencia;
    }

    public String getNumeroTrabajador() {
	return numeroTrabajador;
    }

    public void setNumeroTrabajador(String numeroTrabajador) {
	this.numeroTrabajador = numeroTrabajador.toUpperCase();
    }

    public boolean isSitucionLaboral() {
	return situcionLaboral;
    }

    public void setSitucionLaboral(boolean situcionLaboral) {
	this.situcionLaboral = situcionLaboral;
    }

    public boolean isDirector() {
	return director;
    }

    public void setDirector(boolean director) {
	this.director = director;
    }

    public Peticion getPeticion() {
	return peticion;
    }

    public void setPeticion(Peticion peticion) {
	this.peticion = peticion;
    }
    
    public static class Builder implements EntityBuilder<Trabajador>{
	
	private UUID id;
	private Persona persona;
	private Dependencia dependecia;
	private String numeroTrabajador;
	private boolean situcionLaboral;
	private boolean director;
	private Peticion peticion;
	
	public Builder setPersona(Persona persona){
	    this.persona = checkNotNull(persona);
	    return this;
	}
	
	public Builder setDependencia(Dependencia dependencia){
	    this.dependecia = checkNotNull(dependencia);
	    return this;
	}
	
	public Builder setNumeroTrabajador(String numeroTrabajador){
	    checkState(!isNullOrEmpty(numeroTrabajador));
	    return this;
	}
	
	public Builder setSituacionLaboral(boolean situacionLaboral){
	    return this;
	}
	
	public Builder setDirector(boolean director){
	    return this;
	}
	
	public Builder setPeticion(Peticion peticion){
	    return this;
	}
	
	@Override
	public Trabajador build() {
	    id = UUID.randomUUID();
	    Trabajador trabajador = new Trabajador(this);
	    return trabajador;
	}
    }
}
