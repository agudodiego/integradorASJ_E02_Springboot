package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.exceptions.PlataformaException;
import com.asjservicios.seriesappspringboot.model.Plataforma;

import java.util.List;
import java.util.NoSuchElementException;

public interface PlataformaService {

    List<Plataforma> findAll() throws NoSuchElementException;

}
