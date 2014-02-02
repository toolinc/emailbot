/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence.history.domain;

import com.tool.emailbot.persistence.history.Auditoria;
import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.domain.Aprovacion;
import com.tool.emailbot.persistence.domain.Peticion;
import com.tool.emailbot.persistence.domain.Trabajador;
import com.tool.emailbot.persistence.history.Auditoria;
import com.tool.emailbot.persistence.history.Operacion;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edgar
 */
@Entity
@Table(name = "AprovacionHistoria", uniqueConstraints = {
    @UniqueConstraint(name = "aprovacionHistoriaUK",
            columnNames = {"idAuditoria", "idAprovacion", "idTrabajador"})})
public class AprovacionHistoria extends Entidad {

    @Id
    @Column(name = "idAprovacionHistoria")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAuditoria", nullable = false)
    private Auditoria auditoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoOperacion", nullable = false)
    private Operacion operacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAprovacion", nullable = false)
    private Aprovacion aprovacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private Trabajador director;

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

    public Aprovacion getAprovacion() {
        return aprovacion;
    }

    public void setAprovacion(@NotNull Aprovacion aprovacion) {
        this.aprovacion = aprovacion;
    }

    public Date getAprovadoEn() {
        return newDate(aprovadoEn);
    }

    public void setAprovadoEn(@NotNull @Future Date aprovadoEn) {
        this.aprovadoEn = newDate(aprovadoEn);
    }

    public Trabajador getDirector() {
        return director;
    }

    public void setDirector(@NotNull Trabajador director) {
        this.director = director;
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
}
