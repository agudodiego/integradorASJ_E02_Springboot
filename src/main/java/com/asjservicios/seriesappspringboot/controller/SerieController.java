package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.SerieDTO;
import com.asjservicios.seriesappspringboot.service.SerieService;
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
            return ResponseEntity.ok(optSerie.get());
        }

        response.put("success", Boolean.FALSE);
        response.put("message", String.format("No hay ninguna serie con id %d", id));
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/{nombreUsuario}")
    public SerieDTO guardarSerie(@PathVariable String nombreUsuario, @RequestBody SerieDTO serieDTO) {
        return this.serieService.save(nombreUsuario, serieDTO);
    }
}
