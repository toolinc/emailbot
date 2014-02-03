// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.aa;

import com.tool.emailbot.persistence.Entidad;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents a Rol of a User.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Rol")
public class Rol extends Entidad {

    @NotNull
    @Id
    @Column(name = "idRol")
    private UUID id;

    @NotNull
    @Pattern(regexp = "^[\\\\w ]{5, 255}$")
    @Column(name = "nombre", nullable = false, unique = false)
    private String nombre;

    @OneToMany(mappedBy = "rol")
    private Set<Usuario> usuarios;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
