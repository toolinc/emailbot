// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.Entidad;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 * This class represents an Approval of an email request.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Aprovacion", uniqueConstraints = {
    @UniqueConstraint(name = "aprovacionUK", columnNames = {"idTrabajador", "idPeticion"})})
public class Aprovacion extends Entidad {

    @Id
    @Column(name = "idAprovacion")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private Trabajador direcctor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeticion", nullable = false)
    private Peticion peticion;

    @Column(name = "aprovado", nullable = false)
    private boolean aprovado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "aprovadoEn", nullable = false)
    private Date aprovadoEn;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Trabajador getDirecctor() {
        return direcctor;
    }

    public void setDirecctor(@NotNull Trabajador direcctor) {
        this.direcctor = direcctor;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(@NotNull Peticion peticion) {
        this.peticion = peticion;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Date getAprovadoEn() {
        return newDate(aprovadoEn);
    }

    public void setAprovadoEn(@NotNull @Future Date aprovadoEn) {
        this.aprovadoEn = newDate(aprovadoEn);
    }
}
