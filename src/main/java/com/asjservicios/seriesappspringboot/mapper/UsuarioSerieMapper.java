package com.asjservicios.seriesappspringboot.mapper;

import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;

public class UsuarioSerieMapper {

    public static UsuarioSerieDTO entityToDto (UsuarioSerie entity) {
        UsuarioSerieDTO dto = new UsuarioSerieDTO();
        dto.setId_usuario_serie(entity.getId_usuario_serie());
        dto.setId_usuario(entity.getUsuario().getId_usuario());
        dto.setId_serie(entity.getSerie().getId_serie());
        dto.setTemp_actual(entity.getTemp_actual());
        dto.setEpisod_actual(entity.getEpisod_actual());
        dto.setActiva(entity.getActiva());
        dto.setPlataforma(entity.getPlataforma());
        return dto;
    }
}
