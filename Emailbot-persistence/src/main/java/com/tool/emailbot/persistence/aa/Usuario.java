// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.aa;

import com.tool.emailbot.persistence.Entidad;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class represents a User.
 * 
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Entity
@Table(name = "Usuario")
public class Usuario extends Entidad {

    @NotNull
    @Id
    @Column(name = "idUsuario")
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

    @NotNull
    @Pattern(regexp = "^[\\\\w_]{3,255}$") 
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Column(name = "usuarioSistema", nullable = false)
    private boolean usuarioSistema;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]{3, 45}$") 
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]{3, 45}$") 
    @Column(name = "apellidoPaterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]{3, 45}$") 
    @Column(name = "apellidoMaterno", nullable = false, length = 45)
    private String apellidoMaterno;


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(@NotNull Rol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public boolean isUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(boolean usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
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

}
