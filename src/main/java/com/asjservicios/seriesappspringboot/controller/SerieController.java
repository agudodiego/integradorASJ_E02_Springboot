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
    public ResponseEntity<?> getSerieById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Serie> optSerie = this.serieService.findById(id);
            SerieDTO serieDTO = SerieMapper.entityToDtoSinRelacion(optSerie.get());
            return ResponseEntity.ok(serieDTO);

        } catch (Exception e) {
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No hay ninguna serie con id %d", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/{nombreUsuario}")
    @ApiOperation("Guarda una Serie (en formato SerieDTO) y crea la relacion con el usuario que la agrega")
    public ResponseEntity<?> guardarSerie(@PathVariable String nombreUsuario, @RequestBody SerieDTO serieDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            SerieDTO serieDTORta = this.serieService.save(nombreUsuario, serieDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(serieDTORta);

        }catch (SerieException se) {
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("La serie %s no se pudo agregar", serieDTO.getTitulo()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        }
    }
}
