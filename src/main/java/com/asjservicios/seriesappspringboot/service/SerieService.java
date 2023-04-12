package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.exceptions.SerieException;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SerieService {

    SerieDTO getSerieById(Integer id);

    Optional<Serie> findById(Integer id);

    SerieDTO save(String nombreUsuario, SerieDTO serieDTO) throws SerieException;
}
