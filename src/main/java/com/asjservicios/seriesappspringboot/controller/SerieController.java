package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.SerieException;
import com.asjservicios.seriesappspringboot.mapper.SerieMapper;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.service.SerieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Devuelve una Serie, buscada por su ID (proporcionado por la API de series)")
    public ResponseEntity<?> getSerieById(@PathVariable Integer id) throws NoSuchElementException {

        Optional<Serie> optSerie = this.serieService.findById(id);
        SerieDTO serieDTO = SerieMapper.entityToDtoSinRelacion(optSerie.get());
        return ResponseEntity.ok(serieDTO);
    }

    @PostMapping("/{nombreUsuario}")
    @ApiOperation("Guarda una Serie (en formato SerieDTO) y crea la relacion con el usuario que la agrega")
    public ResponseEntity<?> guardarSerie(@PathVariable String nombreUsuario, @RequestBody SerieDTO serieDTO) throws SerieException {

        SerieDTO serieDTORta = this.serieService.save(nombreUsuario, serieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(serieDTORta);
    }
}
