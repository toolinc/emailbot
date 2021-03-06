// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.common.domain.model.DomainObject;
import com.tool.emailbot.common.domain.model.DomainObjectBuilder;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This class represents a Worker.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Trabajador")
public class Trabajador extends DomainObject {

    @NotNull
    @Id
    @Column(name = "idTrabajador")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            optional = false)
    @JoinColumn(name = "idPersona", nullable = false, unique = true)
    private Persona persona;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "idDependencia", nullable = false)
    private Dependencia dependencia;

    @NotNull
    @Column(name = "numeroTrabajador", nullable = false, length = 50)
    private String numeroTrabajador;

    @NotNull
    @Column(name = "situcionLaboral", nullable = false)
    private SituacionLaboral situcionLaboral;

    @NotNull
    @Column(name = "director", nullable = false)
    private boolean director;

    @Deprecated
    public Trabajador() {
    }

    private Trabajador(Builder builder) {
        this.id = builder.id;
        builder.builderPersona.setTrabajador(this);
        setPersona(builder.builderPersona.build());
        setDependencia(builder.dependecia);
        setDirector(builder.director);
        setNumeroTrabajador(builder.numeroTrabajador);
        setSitucionLaboral(SituacionLaboral.ACTIVO);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = checkNotNull(persona);
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = checkNotNull(dependencia);
    }

    public String getNumeroTrabajador() {
        return numeroTrabajador;
    }

    public void setNumeroTrabajador(String numeroTrabajador) {
        checkState(!isNullOrEmpty(numeroTrabajador));
        this.numeroTrabajador = numeroTrabajador.toUpperCase();
    }

    public SituacionLaboral getSitucionLaboral() {
        return situcionLaboral;
    }

    public void setSitucionLaboral(SituacionLaboral situcionLaboral) {
        this.situcionLaboral = situcionLaboral;
    }

    public boolean isDirector() {
        return director;
    }

    public void setDirector(boolean director) {
        this.director = director;
    }

    /**
     * Builder of {@link Trabajador} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder implements DomainObjectBuilder<Trabajador> {

        private UUID id;
        private Persona.Builder builderPersona;
        private Dependencia dependecia;
        private String numeroTrabajador;
        private boolean director;

        public Builder setPersona(Persona.Builder builderPersona) {
            this.builderPersona = checkNotNull(builderPersona);
            return this;
        }

        public Builder setDependencia(Dependencia dependencia) {
            this.dependecia = checkNotNull(dependencia);
            return this;
        }

        public Builder setNumeroTrabajador(String numeroTrabajador) {
            checkState(!isNullOrEmpty(numeroTrabajador));
            this.numeroTrabajador = numeroTrabajador;
            return this;
        }

        public Builder setDirector(boolean director) {
            this.director = director;
            return this;
        }

        /**
         * Creates a instances of
         * {@link Trabajador} given the specified
         * characteristics on the
         * {@link Trabajador.Builder}.
         *
         * @return a new instance {@link Trabajador}.
         */
        @Override
        public Trabajador build() {
            id = UUID.randomUUID();
            Trabajador trabajador = new Trabajador(this);
            return trabajador;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link Trabajador.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
