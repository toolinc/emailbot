// Copyright 2014 Tool Inc.

package com.tool.emailbot.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.common.domain.model.DomainObject;
import com.tool.emailbot.common.domain.model.DomainObjectBuilder;

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
public class Persona extends DomainObject {

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
    @Column(name = "fechaNacimiento", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    //@Pattern(regexp = NULL_REGEX + "|" + HOMOCLAVE_REGEX)
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
	builder.builderContacto.setPersona(this);
	setInformacionContacto(builder.builderContacto.build());
	setTrabjador(builder.trabajador);
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
	this.trabjador = checkNotNull(trabjador);
    }

    /**
     * Builder of {@link Persona} instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder implements DomainObjectBuilder<Persona> {

	private static final java.util.regex.Pattern pattern
		= java.util.regex.Pattern.compile(HOMOCLAVE_REGEX);

	private UUID id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private String homoclave;
	private InformacionContacto.Builder builderContacto;
	private Trabajador trabajador;

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

	public Builder setInformacionContacto(InformacionContacto.Builder builder) {
	    this.builderContacto = checkNotNull(builder);
	    return this;
	}

	public Builder setTrabajador(Trabajador trabajador) {
	    this.trabajador = checkNotNull(trabajador);
	    return this;
	}

	private static String validateHomoclave(String homoclave) {
	    if (homoclave != null) {
		checkState(pattern.matcher(homoclave).matches(), "Invalid homo clave.");
	    }
	    return homoclave;
	}

	/**
	 * Creates a instances of {@link Persona} given the
	 * specified characteristics on the
	 * {@link Persona.Builder}.
	 *
	 * @return a new instance {@link Persona}.
	 */
	@Override
	public Persona build() {
	    id = UUID.randomUUID();
	    Persona persona = new Persona(this);
	    return persona;
	}

	/**
	 * Provides a new builder.
	 *
	 * @return a new instance of {@link Persona.Builder}.
	 */
	public static Builder newBuilder() {
	    return new Builder();
	}
    }
}
