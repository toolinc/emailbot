// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.EntityBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

/**
 * This class represents a Person.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Persona", uniqueConstraints = {
        @UniqueConstraint(name = "personaUK", columnNames = {"nombre", "apellidoPaterno",
                "apellidoMaterno", "fechaNacimiento"})})
public class Persona extends Entidad {

    private static final String NOMBRE_REGEX = "^[A-Z ]{3,45}$";
    private static final String HOMOCLAVE_REGEX = "^$|^[\\w]{3}$";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMdd");

    @NotNull
    @Id
    @Column(name = "idPersona")
    private UUID id;

    @NotNull
    @Pattern(regexp = NOMBRE_REGEX)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombrePersona;

    @NotNull
    @Pattern(regexp = NOMBRE_REGEX)
    @Column(name = "apellidoPaterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @NotNull
    @Pattern(regexp = NOMBRE_REGEX)
    @Column(name = "apellidoMaterno", nullable = false, length = 45)
    private String apellidoMaterno;

    @NotNull
    @Past
    @Pattern(regexp = HOMOCLAVE_REGEX)
    @Column(name = "fechaNacimiento", nullable = false, length = 45)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @Pattern(regexp = NULL_REGEX + "|" + HOMOCLAVE_REGEX)
    @Column(name = "homoclave", length = 3)
    private String homoclave;

    @NotNull
    @Pattern(regexp = "^[A-Z]{4}[\\d]{6}([\\w]{3})?$")
    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private InformacionContacto informacionContacto;

    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Trabajador trabjador;

    @Deprecated
    public Persona() {
    }

    private Persona(Builder builder) {
        this.id = builder.id;
        setInformacionContacto(builder.informacionContacto);
        setNombre(builder.nombre);
        setApellidoPaterno(builder.apellidoPaterno);
        setApellidoMaterno(builder.apellidoMaterno);
        setFechaNacimiento(builder.fechaNacimiento);
        setHomoclave(builder.homoclave);
        setRfc();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombrePersona;
    }

    public void setNombre(String nombrePersona) {
        this.nombrePersona = nombrePersona.toUpperCase();
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno.toUpperCase();
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno.toUpperCase();
    }

    public Date getFechaNacimiento() {
        return newDate(fechaNacimiento);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = newDate(fechaNacimiento);
    }

    public String getHomoclave() {
        return homoclave;
    }

    public void setHomoclave(String homoclave) {
        this.homoclave = Builder.validateHomoclave(homoclave);
    }

    private String validateHomoclave(String homoclave) {
        return homoclave.toUpperCase();
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc() {
        checkNotNull(getFechaNacimiento());
        checkNotNull(getNombre());
        checkNotNull(getApellidoPaterno());
        checkNotNull(getApellidoMaterno());
        Builder.validateHomoclave(getHomoclave());
        String rfc = getApellidoPaterno().substring(0, 2) + getApellidoMaterno().substring(0, 1)
                + getNombre().substring(0, 1) + DATE_FORMAT.format(getFechaNacimiento());
        if (getHomoclave() != null) {
            rfc += getHomoclave();
        }
        this.rfc = rfc.toUpperCase();
    }

    public InformacionContacto getInformacionContacto() {
        return informacionContacto;
    }

    public void setInformacionContacto(InformacionContacto informacionContacto) {
        this.informacionContacto = checkNotNull(informacionContacto);
    }

    public Trabajador getTrabjador() {
        return trabjador;
    }

    public void setTrabjador(Trabajador trabjador) {
        this.trabjador = trabjador;
    }


    public static class Builder implements EntityBuilder<Persona> {

        private static final java.util.regex.Pattern pattern
                = java.util.regex.Pattern.compile(HOMOCLAVE_REGEX);

        private UUID id;
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private Date fechaNacimiento;
        private String homoclave;
        private InformacionContacto informacionContacto;
        private InformacionContacto.Builder builderContacto;
        private Trabajador trabajador;
        private Trabajador.Builder builderTrabjador;

        public Builder setNombre(String nombre) {
            checkState(!isNullOrEmpty(nombre));
            this.nombre = nombre;
            return this;
        }

        public Builder setApellidoPaterno(String apellidoPaterno) {
            checkState(!isNullOrEmpty(apellidoPaterno));
            this.apellidoPaterno = apellidoPaterno;
            return this;
        }

        public Builder setApellidoMaterno(String apellidoMaterno) {
            checkState(!isNullOrEmpty(apellidoMaterno));
            this.apellidoMaterno = apellidoMaterno;
            return this;
        }

        public Builder setFechaNacimiento(int year, int month, int dayOfMonth) {
            this.fechaNacimiento = newDate(year, month, dayOfMonth);
            return this;
        }

        public Builder setHomoclave(String homoclave) {
            this.homoclave = Builder.validateHomoclave(homoclave);
            return this;
        }

        public Builder setInformacionContacto(InformacionContacto informacionContacto) {
            this.informacionContacto = checkNotNull(informacionContacto);
            return this;
        }

        public Builder setInformacionContacto(InformacionContacto.Builder builder) {
            this.builderContacto = checkNotNull(builder);
            return this;
        }

        public Builder setTrabajador(Trabajador trabajador) {
            this.trabajador = checkNotNull(trabajador);
            return this;
        }

        public Builder setTrabajador(Trabajador.Builder builder) {
            this.builderTrabjador = checkNotNull(builder);
            return this;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        private static String validateHomoclave(String homoclave) {
            if (homoclave != null) {
                checkState(pattern.matcher(homoclave).matches(), "Invalid homo clave.");
            }
            return homoclave;
        }

        @Override
        public Persona build() {
            id = UUID.randomUUID();
            if (builderContacto != null) {
                setInformacionContacto(builderContacto.build());
            }
            if (builderTrabjador != null) {
                setTrabajador(builderTrabjador.build());
            }
            Persona persona = new Persona(this);
            informacionContacto.setPersona(persona);
            return persona;
        }
    }
}