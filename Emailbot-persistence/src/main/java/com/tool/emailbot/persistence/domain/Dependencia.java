// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.EntityBuilder;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents a Branch of an institution.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Dependencia")
public class Dependencia extends Entidad {

    @NotNull
    @Id
    @Column(name = "idDependencia")
    private UUID id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]{1, 44}$") 
    @Column(name = "abreviacion", nullable = false, length = 45, unique = true)
    private String abreviacion;

    @NotNull
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]{1, 254}$")
    @Column(name = "nombre", nullable = false, unique = true)
    private String name;
    
    @OneToOne(mappedBy = "dependencia", fetch = FetchType.LAZY)
    private Trabajador trabajador;

   

    @Deprecated
    public Dependencia(){
    }
    
    private Dependencia(Builder buider){
	this.id = buider.id;
	settrabajador(buider.trabajador);
	setName(buider.nombre);
	setAbreviacion(buider.abreviacion);
    }
    
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }
    
     public Trabajador getTrabajador() {
	return trabajador;
    }

    public void settrabajador(Trabajador trabajador) {
	this.trabajador = trabajador;
    }
     
     
    public static class Builder implements EntityBuilder<Dependencia>{
	
	private UUID id;
	private String nombre;
	private String abreviacion;
	private Trabajador trabajador;
	private Trabajador.Builder builder;

	@Override
	public Dependencia build() {
	    id = UUID.randomUUID();
	    if (builder != null) {
		setTrabajador(builder.build());
	    }
	    Dependencia dependencia = new Dependencia(this);
	    trabajador.setDependencia(dependencia);
	    return dependencia;
	}
	
	public Builder setTrabajador(Trabajador trabajador) {
	    this.trabajador = checkNotNull(trabajador);
	    return this;
	}

	public Builder setTrabajador(Trabajador.Builder builder) {
	    this.builder = checkNotNull(builder);
	    return this;
	}
	
	public static Builder newBuilder() {
	    return new Builder();
	}
	
	public Builder setNombre(String nombre){
	    checkState(!isNullOrEmpty(nombre));
	    this.nombre = nombre;
	    return this;
	}
	
	public Builder setAbreviacion(String abreviacion){
	    checkState(!isNullOrEmpty(abreviacion));
	    this.abreviacion = abreviacion;
	    return this;
	}
    }
}
