// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.Entidad;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents an email request of a {@link Person}.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Peticion")
public class Peticion extends Entidad {

    @Id
    @Column(name = "idPeticion")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false, unique = true)
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Estatus estatus;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @OneToOne(mappedBy = "peticion")
    private Aprovacion aprovacion;

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

    public void setPersona(@NotNull Persona persona) {
        this.persona = persona;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(@NotNull Estatus estatus) {
        this.estatus = estatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Pattern(
            regexp = "^[_\\\\w-\\\\+]+(\\\\.[_\\\\w-]+)*@[\\\\w-]+(\\\\.[\\\\w]+)*(\\\\.[A-Za-z]"
            + "{2,})$") String email) {
        this.email = email.toUpperCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            @NotNull @Pattern(regexp = "^[_\\\\w-\\\\+]+(\\\\.[_\\\\w-]+)*$") String username) {
        this.username = username.toUpperCase();
    }

    public Aprovacion getAprovacion() {
        return aprovacion;
    }

    public void setAprovacion(@NotNull Aprovacion aprovacion) {
        this.aprovacion = aprovacion;
    }
}
