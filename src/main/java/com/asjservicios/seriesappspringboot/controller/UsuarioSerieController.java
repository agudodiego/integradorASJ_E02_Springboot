package com.asjservicios.seriesappspringboot.controller;

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
    public UsuarioSerie getRelacionUsuarioSerie(@PathVariable Integer id) {
        return this.usuarioSerieService.findById(id).get();
    }

    @PostMapping("")
    public ResponseEntity<?> actualizarRelacion(@RequestBody UsuarioSerie relacion) {

        Map<String, Object> response = new HashMap<>();
        Optional<UsuarioSerie> optRelacion = this.usuarioSerieService.updateRelacion(relacion);

        if(optRelacion.isPresent()) {
            return ResponseEntity.ok(optRelacion.get());
        }

        response.put("success", Boolean.FALSE);
        response.put("message", "La relacion entre el usuario y la serie no existe");
        return ResponseEntity.badRequest().body(response);
    }
}
