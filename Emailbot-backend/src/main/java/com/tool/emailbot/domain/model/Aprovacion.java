// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.tool.emailbot.common.domain.model.DomainObject;
import com.tool.emailbot.common.domain.model.DomainObjectBuilder;

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
public class Aprovacion extends DomainObject {

    @NotNull
    @Id
    @Column(name = "idAprovacion")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajador", nullable = false)
    private Trabajador direcctor;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeticion", nullable = false)
    private Peticion peticion;

    @NotNull
    @Column(name = "aprovado", nullable = false)
    private TipoAprovacion aprovado;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Future
    @Column(name = "aprovadoEn", nullable = false)
    private Date aprovadoEn;

    @Deprecated
    public Aprovacion() {
    }

    private Aprovacion(Builder builder) {
        this.id = builder.id;
        setDirecctor(builder.director);
        setPeticion(builder.peticion);
        setAprovado(builder.tipoAprovacion);
        setAprovadoEn(builder.aprovadoEn);
    }

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

    public void setDirecctor(Trabajador direcctor) {
        this.direcctor = checkNotNull(direcctor);
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = checkNotNull(peticion);
    }

    public TipoAprovacion getAprovado() {
        return aprovado;
    }

    public void setAprovado(TipoAprovacion aprovado) {
        this.aprovado = checkNotNull(aprovado);
    }

    public Date getAprovadoEn() {
        return newDate(aprovadoEn);
    }

    public void setAprovadoEn(Date aprovadoEn) {
        this.aprovadoEn = newDate(aprovadoEn);
    }

    /**
     * Builder of {@link Aprovacion} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder implements DomainObjectBuilder<Aprovacion> {

        private UUID id;
        private Trabajador director;
        private Peticion peticion;
        private TipoAprovacion tipoAprovacion;
        private Date aprovadoEn;

        public Builder setDirector(Trabajador director) {
            this.director = checkNotNull(director);
            return this;
        }

        public Builder setPeticion(Peticion peticion) {
            this.peticion = checkNotNull(peticion);
            return this;
        }

        public Builder setTipoAprovacion(TipoAprovacion tipoAprovacion) {
            this.tipoAprovacion = checkNotNull(tipoAprovacion);
            return this;
        }

        public Builder setAprovadoEn(Date aprovadoEn) {
            this.aprovadoEn = checkNotNull(aprovadoEn);
            return this;
        }

        /**
         * Creates a instances of {@link Aprovacion} given the
         * specified characteristics on the
         * {@link Aprovacion.Builder}.
         *
         * @return a new instance {@link Aprovacion}.
         */
        @Override
        public Aprovacion build() {
            id = UUID.randomUUID();
            Aprovacion aprovacion = new Aprovacion(this);
            return aprovacion;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link Aprovacion.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
