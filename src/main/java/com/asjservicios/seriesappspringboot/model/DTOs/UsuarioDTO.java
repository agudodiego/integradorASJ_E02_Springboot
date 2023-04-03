package com.asjservicios.seriesappspringboot.model.DTOs;

import javax.validation.constraints.*;

import java.util.Collection;

public class UsuarioDTO {

    private Integer id_usuario;

    @Size(min = 5, message = "El usuario debe tener como minimo 5 caracteres")
//    @NotBlank(message = "El usuario no puede estar en blanco")
    private String usuario;

    @Size(min = 8, message = "La contraseña debe tener como minimo 8 caracteres")
//    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String contrasenia;

    private String nuevaContrasenia;

    @Email(message = "Debe ser una direccion de mail valida")
    private String email;
    private Collection<SerieDTO> usuarioSeries;

    public UsuarioDTO() {
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNuevaContrasenia() {
        return nuevaContrasenia;
    }

    public void setNuevaContrasenia(String nuevaContrasenia) {
        this.nuevaContrasenia = nuevaContrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<SerieDTO> getUsuarioSeries() {
        return usuarioSeries;
    }

    public void setUsuarioSeries(Collection<SerieDTO> usuarioSeries) {
        this.usuarioSeries = usuarioSeries;
    }
}
