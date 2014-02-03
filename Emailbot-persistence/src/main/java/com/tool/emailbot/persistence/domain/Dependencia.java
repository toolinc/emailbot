// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.Entidad;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
