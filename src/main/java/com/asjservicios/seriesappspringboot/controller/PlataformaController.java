package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.PlataformaException;
import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.service.PlataformaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlataformaController {

    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @GetMapping("/plataformas")
    public ResponseEntity<?> getAllPlataformas() {

        Map<String, Object> response = new HashMap<>();

        try {
            List<Plataforma> plataformas = this.plataformaService.findAll();
            return ResponseEntity.ok(plataformas);

        } catch (PlataformaException pe) {

            response.put("success", Boolean.FALSE);
            response.put("message", "Usuario y/o contraseña incorrecto/s");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
    }
}
