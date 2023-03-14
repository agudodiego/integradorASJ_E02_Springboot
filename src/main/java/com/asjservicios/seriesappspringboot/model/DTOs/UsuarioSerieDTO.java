package com.asjservicios.seriesappspringboot.model.DTOs;

import com.asjservicios.seriesappspringboot.model.Plataforma;

public class UsuarioSerieDTO {

    private Integer id_usuario_serie;
    private Integer id_usuario;
    private Integer id_serie;
    private Integer temp_actual;
    private Integer episod_actual;
    private Boolean activa;
    private Plataforma plataforma;

    public UsuarioSerieDTO() {
    }

    public Integer getId_usuario_serie() {
        return id_usuario_serie;
    }

    public void setId_usuario_serie(Integer id_usuario_serie) {
        this.id_usuario_serie = id_usuario_serie;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_serie() {
        return id_serie;
    }

    public void setId_serie(Integer id_serie) {
        this.id_serie = id_serie;
    }

    public Integer getTemp_actual() {
        return temp_actual;
    }

    public void setTemp_actual(Integer temp_actual) {
        this.temp_actual = temp_actual;
    }

    public Integer getEpisod_actual() {
        return episod_actual;
    }

    public void setEpisod_actual(Integer episod_actual) {
        this.episod_actual = episod_actual;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }
}
