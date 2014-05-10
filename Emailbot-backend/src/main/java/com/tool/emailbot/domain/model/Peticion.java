// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.common.domain.model.DomainObject;
import com.tool.emailbot.common.domain.model.DomainObjectBuilder;
import com.tool.emailbot.common.domain.validation.Email;

import java.util.UUID;

import javax.persistence.CascadeType;
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

/**
 * This class represents an email request of a {@link Trabajador}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Peticion")
public class Peticion extends DomainObject {

    @NotNull
    @Id
    @Column(name = "idPeticion")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "idTrabajador", nullable = false, unique = true)
    private Trabajador trabajador;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Estatus estatus;

    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    //@Pattern(regexp = USER_NAME_REGEX)
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @OneToOne(mappedBy = "peticion", cascade = {CascadeType.REFRESH})
    private Aprovacion aprovacion;

    @Deprecated
    public Peticion() {
    }

    private Peticion(Builder builder) {
        this.id = builder.id;
        setTrabajador(builder.trabajador);
        setEstatus(builder.estatus);
        setUsername(builder.username);
        setEmail(builder.email);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = checkNotNull(trabajador);
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = checkNotNull(estatus);
    }

    public String getEmail() {
        return email;
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

    public Aprovacion getAprovacion() {
        return aprovacion;
    }

    public void setAprovacion(Aprovacion aprovacion) {
        this.aprovacion = aprovacion;
    }

    /**
     * This method transition a request form SOLICITUD to the next state based on the worker status,
     * If the worker is active it will transition to PENDIENTE status otherwise will transition to
     * RECHAZADA.
     */
    public void transitionFromSolicitud() {
        if (Estatus.SOLICITUD.equals(getEstatus())) {
            if (getTrabajador().getSitucionLaboral().isActive()) {
                setEstatus(Estatus.PENDIENTE);
            } else {
                setEstatus(Estatus.RECHAZADA);
            }
        }
    }

    /**
     * This method transition a request form PENDIENTE to the next state based on the worker status.
     */
    public void transitionFromPendiente() {
        if (Estatus.PENDIENTE.equals(getEstatus())) {
            setEstatus(Estatus.NOTIFICADO);
        }
    }

    /**
     * Builder of {@link Peticion} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder implements DomainObjectBuilder<Peticion> {

        private UUID id;
        private Trabajador trabajador;
        private Estatus estatus = Estatus.PENDIENTE;
        private String email;
        private String username;

        public Builder setTrabajador(Trabajador trabajador) {
            this.trabajador = checkNotNull(trabajador);
            return this;
        }

        public Builder setEmail(String email) {
            checkState(!isNullOrEmpty(email));
            this.email = email;
            return this;
        }

        public Builder setUsername(String username) {
            checkState(!isNullOrEmpty(username));
            this.username = username;
            return this;
        }

        /**
         * Creates a instances of {@link Peticion} given the specified characteristics on the
         * {@link Peticion.Builder}.
         *
         * @return a new instance {@link com.tool.emailbot.domain.model.Peticion}.
         */
        @Override
        public Peticion build() {
            id = UUID.randomUUID();
            Peticion peticion = new Peticion(this);
            return peticion;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of {@link com.tool.emailbot.domain.model.Peticion.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
