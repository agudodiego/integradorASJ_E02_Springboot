package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.mapper.UsuarioSerieMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.service.UsuarioSerieService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/relaciones")
public class UsuarioSerieController {

    private final UsuarioSerieService usuarioSerieService;

    @GetMapping("/{id}")
    @ApiOperation("Devuelve una relacion entre un usuario y una serie en su coleccion")
    public ResponseEntity<?> getRelacionUsuarioSerie(@PathVariable Integer id) throws RelacionException {

        return ResponseEntity.ok(usuarioSerieService.findById(id));
    }

    @PutMapping("")
    @ApiOperation("Actualiza una relacion (modificacion de algun dato de la tabla intermedia)")
    public ResponseEntity<?> actualizarRelacion(@RequestBody UsuarioSerieDTO relacionDTO) throws RelacionException {

        return ResponseEntity.ok(usuarioSerieService.updateRelacion(relacionDTO));
    }
}
