package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.PlataformaException;
import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.service.PlataformaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlataformaController {

    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @GetMapping("/plataformas")
    @ApiOperation("Devuelve una lista de las plataformas que puede seleccionar el usuario")
    public ResponseEntity<?> getAllPlataformas() throws NoSuchElementException {
            List<Plataforma> plataformas = this.plataformaService.findAll();
            return ResponseEntity.ok(plataformas);
    }
}
