// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.Entidad;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents the contact information of a  {@code Person}.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "InformacionContacto")
public class InformacionContacto extends Entidad {

    @Id
    @Column(name = "idInformacionContacto")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false, unique = true)
    private Persona persona;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "extension", length = 5, nullable = false)
    private String extension;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email.toUpperCase();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotNull @Pattern(regexp = "^[0-9]{7,15}$") String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(@NotNull @Pattern(regexp = "^[0-9]{1,5}$") String extension) {
        this.extension = extension;
    }
}
