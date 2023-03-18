package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.mapper.SerieMapper;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.service.SerieService;
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
    public ResponseEntity<?> getSerieById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Serie> optSerie = this.serieService.findById(id);
        if (optSerie.isPresent()) {
            SerieDTO serieDTO = SerieMapper.entityToDtoSinRelacion(optSerie.get());
            return ResponseEntity.ok(serieDTO);
        }

        response.put("success", Boolean.FALSE);
        response.put("message", String.format("No hay ninguna serie con id %d", id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/{nombreUsuario}")
    public ResponseEntity<?> guardarSerie(@PathVariable String nombreUsuario, @RequestBody SerieDTO serieDTO) {
        Map<String, Object> response = new HashMap<>();

        SerieDTO serieDTORta = this.serieService.save(nombreUsuario, serieDTO);
        if (serieDTORta != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serieDTORta);
        }

        response.put("success", Boolean.FALSE);
        response.put("message", String.format("La serie %s no se pudo agregar", serieDTO.getTitulo()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
