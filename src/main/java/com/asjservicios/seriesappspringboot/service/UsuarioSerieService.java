package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.model.SerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;

import java.util.Optional;

public interface UsuarioSerieService {

    Optional<UsuarioSerie> findById(Integer id);
    Optional<UsuarioSerie> updateRelacion(UsuarioSerie relacion);

}
