// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.EntityBuilder;
import com.tool.emailbot.persistence.validation.Email;

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
import javax.validation.constraints.Pattern;

/**
 * This class represents an email request of a
 * {@link com.tool.emailbot.persistence.domain.Trabajador}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Peticion")
public class Peticion extends Entidad {

    @NotNull
    @Id
    @Column(name = "idPeticion")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTrabajador", nullable = false, unique = true)
    private Trabajador trabajador;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private Estatus estatus;

    @Email
    //@Pattern(regexp = NULL_REGEX + "|" + EMAIL_REGEX)
    @Column(name = "email")
    private String email;

    @NotNull
    @Pattern(regexp = USER_NAME_REGEX)
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
     * Builder of {@link com.tool.emailbot.persistence.domain.Peticion} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder implements EntityBuilder<Peticion> {
        private UUID id;
        private Trabajador trabajador;
        private Estatus estatus = Estatus.SOLICITUD;
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
            checkState(!isNullOrEmpty(email));
            this.email = email;
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.tool.emailbot.persistence.domain.Peticion} given the specified
         * characteristics on the
         * {@link com.tool.emailbot.persistence.domain.Peticion.Builder}.
         *
         * @return a new instance {@link com.tool.emailbot.persistence.domain.Peticion}.
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
         * @return a new instance of
         * {@link com.tool.emailbot.persistence.domain.Trabajador.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
