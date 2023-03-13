package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/{nombre}")
    public ResponseEntity<?> getUsuarioByName(@PathVariable String nombre, @RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();

        Optional<Usuario> optUsuario = this.usuarioService.validarUsuario(nombre, usuario);
        if ( optUsuario.isPresent()) {
            return ResponseEntity.ok(optUsuario.get());
        }

        response.put("success", Boolean.FALSE);
        response.put("message", "Usuario y/o contraseña incorrecto/s");
        return ResponseEntity.badRequest().body(response);
    }


    @PostMapping("")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {

//        if (usuario.getUsuario().length() >= 5 && usuario.getContrasenia().length() >= 8 && usuario.getEmail().contains("@")) {
//            return this.usuarioService.save(usuario);
//        }

        Map<String, Object> response = new HashMap<>();

        Usuario u = this.usuarioService.save(usuario);
        if ( u == null) {
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El suario %s ya existe", usuario.getUsuario()));
            return ResponseEntity.badRequest().body(response);
        }

        response.put("success", Boolean.TRUE);
        response.put("data", u);
        return ResponseEntity.ok(u);
    }
}
