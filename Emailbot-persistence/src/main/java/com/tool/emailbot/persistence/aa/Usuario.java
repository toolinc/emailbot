/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author edgar
 */
@Entity
@Table(name = "Usuario")
public class Usuario extends Entidad {

    @Id
    @Column(name = "idUsuario")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "usuarioSistema", nullable = false)
    private boolean usuarioSistema;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellidoPaterno", nullable = false, length = 45)
    private String apellidoPaterno;

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

    public void setUsername(@NotNull @Pattern(regexp = "^[\\\\w_]{3,255}$") String username) {
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

    public void setNombre(@NotNull @Pattern(regexp = "^[a-zA-Z ]{3, 45}$") String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(
            @NotNull @Pattern(regexp = "^[a-zA-Z ]{3, 45}$") String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno.toUpperCase();
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(
            @NotNull @Pattern(regexp = "^[a-zA-Z ]{3, 45}$") String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno.toUpperCase();
    }

}
