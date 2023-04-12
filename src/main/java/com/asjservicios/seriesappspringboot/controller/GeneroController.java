package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.model.Genero;
import com.asjservicios.seriesappspringboot.repository.GeneroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // Annotation (de Lombok) para eliminar los constructores
@CrossOrigin(origins = "http://localhost:4200")
//@Api(value = "Controlador de Generos", tags = "Acciones permitidas para la entidad Genero")
@RestController
public class GeneroController {

    private final GeneroRepository generoRepository;

    @GetMapping("/generos")
    @ApiOperation("Devuelve una lista de los generos disponibles a ser usados por la entidad Serie")
    public ResponseEntity<?> traerGeneros() {
        return ResponseEntity.ok((List<Genero>) generoRepository.findAll());
    }
}
