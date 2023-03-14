package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;

import java.util.Optional;

public interface SerieService {

    Optional<Serie> findById(Integer id);
    SerieDTO save(String nombreUsuario, SerieDTO serieDTO);
}
