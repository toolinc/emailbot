/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence.history.domain;

import com.tool.emailbot.persistence.history.Auditoria;
import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.domain.Estatus;
import com.tool.emailbot.persistence.domain.Persona;
import com.tool.emailbot.persistence.domain.Peticion;
import com.tool.emailbot.persistence.history.Auditoria;
import com.tool.emailbot.persistence.history.Operacion;
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
 *
 * @author edgar
 */
@Entity
@Table(name = "PeticionHistoria", uniqueConstraints = {
    @UniqueConstraint(name = "peticionHistoriaUK",
            columnNames = {"idAuditoria", "idPeticion", "idPersona"})})
public class PeticionHistoria extends Entidad {

    @Id
    @Column(name = "idPeticionHistoria")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAuditoria", nullable = false)
    private Auditoria auditoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoOperacion", nullable = false)
    private Operacion operacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeticion", nullable = false)
    private Peticion peticion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false)
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Estatus estatus;

    @Column(name = "email", nullable = false)
    private String email;

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

    public void setAuditoria(@NotNull Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(@NotNull Operacion operacion) {
        this.operacion = operacion;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(@NotNull Peticion peticion) {
        this.peticion = peticion;
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
}
