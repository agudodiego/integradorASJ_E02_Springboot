package com.asjservicios.seriesappspringboot.model.DTOs;

import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;

import java.util.Collection;

public class UsuarioDTO {

    private Integer id_usuario;
    private String usuario;
    private String contrasenia;
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
