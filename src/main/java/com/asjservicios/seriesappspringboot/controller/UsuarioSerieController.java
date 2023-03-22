package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.mapper.UsuarioSerieMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.service.UsuarioSerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/relaciones")
public class UsuarioSerieController {

    private final UsuarioSerieService usuarioSerieService;

    public UsuarioSerieController(UsuarioSerieService usuarioSerieService) {
        this.usuarioSerieService = usuarioSerieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRelacionUsuarioSerie(@PathVariable Integer id) {

        Map<String, Object> response = new HashMap<>();

        try {
            UsuarioSerie optUsuarioSerie = this.usuarioSerieService.findById(id).get();
            UsuarioSerieDTO UsuarioSerieDTO = UsuarioSerieMapper.entityToDto(optUsuarioSerie);
            return ResponseEntity.ok(UsuarioSerieDTO);

        }catch (Exception re) {
            response.put("success", Boolean.FALSE);
            response.put("message", "La relacion no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarRelacion(@RequestBody UsuarioSerieDTO relacionDTO) {

        Map<String, Object> response = new HashMap<>();

        try {
            UsuarioSerieDTO relDTO = this.usuarioSerieService.updateRelacion(relacionDTO);
            return ResponseEntity.ok(relDTO);
            
        }catch (RelacionException re) {

            response.put("success", Boolean.FALSE);
            response.put("message", "La relacion entre el usuario y la serie no existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
