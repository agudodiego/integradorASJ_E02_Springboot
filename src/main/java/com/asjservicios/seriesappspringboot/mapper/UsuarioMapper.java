package com.asjservicios.seriesappspringboot.mapper;

import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioDTO;
import com.asjservicios.seriesappspringboot.model.Usuario;

import java.util.List;

public class UsuarioMapper {

    public static UsuarioDTO entityToDto(Usuario entity, List<SerieDTO> series) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId_usuario(entity.getId_usuario());
        dto.setUsuario(entity.getUsuario());
        dto.setUsuarioSeries(series);
        return dto;
    }

    public static UsuarioDTO entityToDtoCambioContrasenia(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId_usuario(entity.getId_usuario());
        dto.setUsuario(entity.getUsuario());
        return dto;
    }

}
