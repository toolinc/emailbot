// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.common.domain.model.DomainObject;
import com.tool.emailbot.common.domain.model.DomainObjectBuilder;
import com.tool.emailbot.common.domain.validation.Email;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the contact information of a {@link Persona}.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "InformacionContacto")
public class InformacionContacto extends DomainObject {

    @NotNull
    @Id
    @Column(name = "idInformacionContacto")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = false, unique = true)
    private Persona persona;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    //@Pattern(regexp = NULL_REGEX + "|^[0-9]{7,15}$")
    @Size(min = 8)
    @Column(name = "telefono", length = 15)
    private String telefono;

    //@Pattern(regexp = NULL_REGEX + "|^[0-9]{1,5}$")
    @Column(name = "extension", length = 5)
    private String extension;

    @Deprecated
    public InformacionContacto() {
    }

    private InformacionContacto(Builder builder) {
        this.id = builder.id;
        setPersona(builder.persona);
        setEmail(builder.email);
        setTelefono(builder.telefono);
        setExtension(builder.extension);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        checkState(!isNullOrEmpty(email));
        this.email = email.toUpperCase();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Builder of {@link InformacionContacto} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder implements DomainObjectBuilder<InformacionContacto> {

        private UUID id;
        private String email;
        private String telefono;
        private String extension;
        private Persona persona;

        public Builder setPersona(Persona persona) {
            this.persona = checkNotNull(persona);
            return this;
        }

        public Builder setEmail(String email) {
            checkState(!isNullOrEmpty(email));
            this.email = email;
            return this;
        }

        public Builder setTelefono(String telefono) {
            checkState(!isNullOrEmpty(telefono));
            this.telefono = telefono;
            return this;
        }

        public Builder setExtension(String extension) {
            checkState(!isNullOrEmpty(extension));
            this.extension = extension;
            return this;
        }

        /**
         * Creates a instances of
         * {@link InformacionContacto} given the specified
         * characteristics on the
         * {@link InformacionContacto.Builder}.
         *
         * @return a new instance {@link InformacionContacto}.
         */
        @Override
        public InformacionContacto build() {
            id = UUID.randomUUID();
            InformacionContacto informacionContacto = new InformacionContacto(this);
            return informacionContacto;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link InformacionContacto.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
