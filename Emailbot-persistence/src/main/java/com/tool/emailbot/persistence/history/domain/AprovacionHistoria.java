// Copyright 2014 Tool Inc

package com.tool.emailbot.persistence.history.domain;

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
 * This class represents a History of all the approval for a request.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */

@Entity
@Table(name = "AprovacionHistoria", uniqueConstraints = {
    @UniqueConstraint(name = "aprovacionHistoriaUK",
            columnNames = {"idAuditoria", "idAprovacion", "idTrabajador"})})
public class AprovacionHistoria extends Entidad {

    @NotNull
    @Id
    @Column(name = "idAprovacionHistoria")
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
    @JoinColumn(name = "idAprovacion", nullable = false)
    private Aprovacion aprovacion;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private Trabajador director;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeticion", nullable = false)
    private Peticion peticion;

    @NotNull
    @Column(name = "aprovado", nullable = false)
    private boolean aprovado;

    @NotNull
    @Future 
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

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Aprovacion getAprovacion() {
        return aprovacion;
    }

    public void setAprovacion(Aprovacion aprovacion) {
        this.aprovacion = aprovacion;
    }

    public Date getAprovadoEn() {
        return newDate(aprovadoEn);
    }

    public void setAprovadoEn(Date aprovadoEn) {
        this.aprovadoEn = newDate(aprovadoEn);
    }

    public Trabajador getDirector() {
        return director;
    }

    public void setDirector(Trabajador director) {
        this.director = director;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
}
