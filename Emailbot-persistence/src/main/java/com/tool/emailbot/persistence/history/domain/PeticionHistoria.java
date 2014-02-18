// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.history.domain;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.domain.Estatus;
import com.tool.emailbot.persistence.domain.Persona;
import com.tool.emailbot.persistence.domain.Peticion;
import com.tool.emailbot.persistence.history.Auditoria;
import com.tool.emailbot.persistence.history.Operacion;
import com.tool.emailbot.persistence.validation.Email;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents a history of all requests make for the users.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "PeticionHistoria", uniqueConstraints = {
    @UniqueConstraint(name = "peticionHistoriaUK",
            columnNames = {"idAuditoria", "idPeticion", "idPersona"})})
public class PeticionHistoria extends Entidad {

    @NotNull
    @Id
    @Column(name = "idPeticionHistoria")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAuditoria", nullable = false)
    private Auditoria auditoria;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoOperacion", nullable = false)
    private Operacion operacion;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeticion", nullable = false)
    private Peticion peticion;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false)
    private Persona persona;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Estatus estatus;

    @NotNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Pattern(regexp = "^[_\\w]+(\\.[_\\w-]+)*$")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }
}
