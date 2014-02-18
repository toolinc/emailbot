// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.history;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.aa.Usuario;

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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 * This class represents an Auditory.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Auditoria")
public class Auditoria extends Entidad {

    @NotNull
    @Id
    @Column(name = "idAuditoria")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @NotNull
    @Future
    @Column(name = "evento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date evento;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getEvento() {
        return newDate(evento);
    }

    public void setEvento(Date evento) {
        this.evento = newDate(evento);
    }
}
