package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.mapper.UsuarioSerieMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.service.UsuarioSerieService;
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
    public UsuarioSerieDTO getRelacionUsuarioSerie(@PathVariable Integer id) {

        UsuarioSerie optUsuarioSerie = this.usuarioSerieService.findById(id).get();
        UsuarioSerieDTO UsuarioSerieDTO = UsuarioSerieMapper.entityToDto(optUsuarioSerie);
        return UsuarioSerieDTO;

    }

    @PutMapping("")
    public ResponseEntity<?> actualizarRelacion(@RequestBody UsuarioSerieDTO relacionDTO) {

        Map<String, Object> response = new HashMap<>();
        UsuarioSerieDTO relDTO = this.usuarioSerieService.updateRelacion(relacionDTO);

        if(relDTO != null) {
            return ResponseEntity.ok(relDTO);
        }

        response.put("success", Boolean.FALSE);
        response.put("message", "La relacion entre el usuario y la serie no existe");
        return ResponseEntity.badRequest().body(response);
    }
}
