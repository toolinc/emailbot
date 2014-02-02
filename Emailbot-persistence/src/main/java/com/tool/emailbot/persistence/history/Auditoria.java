/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author edgar
 */
@Entity
@Table(name = "Auditoria")
public class Auditoria extends Entidad {

    @Id
    @Column(name = "idAuditoria")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

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

    public void setUsuario(@NotNull Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getEvento() {
        return newDate(evento);
    }

    public void setEvento(@NotNull @Future Date evento) {
        this.evento = newDate(evento);
    }
}
