package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;

import java.util.Optional;

public interface UsuarioSerieService {

    Optional<UsuarioSerie> findById(Integer id) throws RelacionException;
    UsuarioSerieDTO updateRelacion(UsuarioSerieDTO relacionDTO) throws RelacionException;

}
