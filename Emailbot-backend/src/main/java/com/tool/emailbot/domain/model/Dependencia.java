// Copyright 2014 Tool Inc. 

package com.tool.emailbot.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.common.AssertionConcern;
import com.tool.emailbot.common.domain.model.DomainObject;
import com.tool.emailbot.common.domain.model.DomainObjectBuilder;
import com.tool.emailbot.common.domain.validation.UniqueKey;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents a Branch of an institution.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Dependencia")
@UniqueKey(columnNames = {"abreviacion"})
public class Dependencia extends DomainObject {

    @NotNull
    @Id
    @Column(name = "idDependencia")
    private UUID id;

    @NotNull
    @Pattern(regexp = "^[A-Z][A-Z ]{2,45}$")
    @Column(name = "abreviacion", nullable = false, length = 45, unique = true)
    private String abreviacion;

    @NotNull
    @Pattern(regexp = "^[A-Z][A-Z ]{5,255}$")
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Deprecated
    public Dependencia() {
    }

    private Dependencia(Builder buider) {
        this.id = buider.id;
        setNombre(buider.nombre);
        setAbreviacion(buider.abreviacion);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = checkNotNull(id);
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        checkState(!isNullOrEmpty(abreviacion));
        this.abreviacion = abreviacion.toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        checkState(!isNullOrEmpty(nombre));
        this.nombre = nombre.toUpperCase();
    }

    /**
     * Builder of {@link Dependencia} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<Dependencia> {

        private UUID id;
        private String nombre = "Universidad Nacional Autonoma de Mexico";
        private String abreviacion = "UNAM";

        public Builder setNombre(String nombre) {
            assertArgumentNotEmpty(nombre, "Nombre cannot be null or empty.");
            this.nombre = nombre.toUpperCase();
            return this;
        }

        public Builder setAbreviacion(String abreviacion) {
            assertArgumentNotEmpty(abreviacion, "Abreviacion cannot be null or empty.");
            this.abreviacion = abreviacion.toUpperCase();
            return this;
        }

        /**
         * Creates a instances of
         * {@link Dependencia} given the specified
         * characteristics on the
         * {@link Dependencia.Builder}.
         *
         * @return a new instance {@link Dependencia}.
         */
        @Override
        public Dependencia build() {
            id = UUID.randomUUID();
            Dependencia dependencia = new Dependencia(this);
            return dependencia;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link Dependencia.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
