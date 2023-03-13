package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.model.Genero;
import com.asjservicios.seriesappspringboot.repository.GeneroRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GeneroController {

    private final GeneroRepository generoRepository;

    public GeneroController(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @GetMapping("/generos")
    public List<Genero> traerGeneros() {
        List<Genero> generos = (List<Genero>) this.generoRepository.findAll();
        return generos;
    }
}
