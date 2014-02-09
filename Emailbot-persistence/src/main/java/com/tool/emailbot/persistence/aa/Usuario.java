// Copyright 2014 Tool Inc. 

package com.tool.emailbot.persistence.aa;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.EntityBuilder;
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


     @Deprecated
    public Usuario(){
    }
    
    private Usuario(Builder builder) {
	this.id = builder.id;
	setRol(builder.rol);
	setUsername(builder.username);
	setNombre(builder.nombre);
	setApellidoPaterno(builder.apellidoPaterno);
	setApellidoMaterno(builder.apellidoMaterno);
        setUsuarioSistema(builder.usuarioSistema);
    }
    
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

     public static class Builder implements EntityBuilder<Usuario>{
        
        private UUID id;
        private String username;
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private boolean usuarioSistema;
	private Rol rol;

        @Override
        public Usuario build() {
            id = UUID.randomUUID();
	    Usuario usuario = new Usuario(this);
	    return usuario;
        }
	
	public static Builder newBuilder() {
	    return new Builder();
	}
        
        public Builder setUserName(String username){
	    checkState(!isNullOrEmpty(username));
	    this.username = username;
	    return this;
	}
	
	public Builder setNombre(String nombre){
	    checkState(!isNullOrEmpty(nombre));
	    this.nombre = nombre;
	    return this;
	}
	
	public Builder setApellidoPaterno(String apellidoPaterno){
	    checkState(!isNullOrEmpty(apellidoPaterno));
	    this.apellidoPaterno = apellidoPaterno;
	    return this;
	}
	
	public Builder setApellidoMaterno(String apellidoMaterno){
	    checkState(!isNullOrEmpty(apellidoMaterno));
	    this.apellidoMaterno = apellidoMaterno;
	    return this;
	}
        
        public Builder setUsuarioSistema(boolean usuarioSistema){
	    this.usuarioSistema = usuarioSistema;
	    return this;
	}
	
	public Builder setRol(Rol rol){
	    checkNotNull(rol);
	    this.rol = rol;
	    return this;
	}
    }
}
