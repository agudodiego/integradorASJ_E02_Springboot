package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;

public interface UsuarioSerieService {

    UsuarioSerieDTO findById(Integer id) throws RelacionException;
    UsuarioSerieDTO updateRelacion(UsuarioSerieDTO relacionDTO) throws RelacionException;

}
