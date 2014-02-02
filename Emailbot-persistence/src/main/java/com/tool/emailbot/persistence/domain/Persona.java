// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.Entidad;

import java.util.Date;
import java.util.UUID;

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

    @NotNull
    @Id
    @Column(name = "idPersona")
    private UUID id;

    @NotNull @Pattern(regexp = "^[A-Z ]{3,45}$") 
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombrePersona;

    @NotNull @Pattern(regexp = "^[A-Z ]{3,45}$") 
    @Column(name = "apellidoPaterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @NotNull @Pattern(regexp = "^[A-Z ]{3,45}$") 
    @Column(name = "apellidoMaterno", nullable = false, length = 45)
    private String apellidoMaterno;

    @NotNull @Past 
    @Column(name = "fechaNacimiento", nullable = false, length = 45)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    @Pattern(regexp = "^$|^[\\w]{3}$") 
    @Column(name = "homoclave", length = 3)
    private String homoclave;

    @NotNull @Pattern(regexp = "^[A-Z]{4}[\\d]{6}([\\w]{3})?$") 
    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private InformacionContacto informacionContacto;

    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Trabajador trabjador;

    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Peticion peticion;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
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
        if (homoclave != null) {
            this.homoclave = validateHomoclave(homoclave);
        } else {
            this.homoclave = null;
        }
    }

    private String validateHomoclave(String homoclave) {
        return homoclave.toUpperCase();
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc.toUpperCase();
    }

    public InformacionContacto getInformacionContacto() {
        return informacionContacto;
    }

    public void setInformacionContacto(
            InformacionContacto informacionContacto) {
        this.informacionContacto = informacionContacto;
    }

    public Trabajador getTrabjador() {
        return trabjador;
    }

    public void setTrabjador(Trabajador trabjador) {
        this.trabjador = trabjador;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }
}
