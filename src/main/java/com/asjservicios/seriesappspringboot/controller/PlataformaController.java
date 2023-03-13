package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.service.PlataformaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlataformaController {

    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @GetMapping("/plataformas")
    public ResponseEntity<List<Plataforma>> getAllPlataformas() {
        List<Plataforma> plataformas = this.plataformaService.findAll();
        return ResponseEntity.ok(plataformas);
    }
}
