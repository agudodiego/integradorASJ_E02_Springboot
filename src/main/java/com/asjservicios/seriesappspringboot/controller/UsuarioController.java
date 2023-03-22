package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.UsuarioException;
import com.asjservicios.seriesappspringboot.mapper.UsuarioMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioDTO;
import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.service.UsuarioService;
import org.springframework.http.HttpStatus;
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

        try {
            UsuarioDTO UsuarioDTO = this.usuarioService.traerUsuarioDTOCompleto(nombre, usuario);
            return ResponseEntity.ok(UsuarioDTO);

        } catch (UsuarioException ue) {

            response.put("success", Boolean.FALSE);
            response.put("message", "Usuario y/o contraseña incorrecto/s");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @PostMapping("")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();

        try {
            Usuario u = this.usuarioService.save(usuario);
            response.put("success", Boolean.TRUE);
            response.put("message", String.format("El suario %s fue creado", u.getUsuario()));
            response.put("data", u);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        }catch (UsuarioException ue) {
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El suario %s ya existe", usuario.getUsuario()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity updateContrasenia(@PathVariable String nombre, @RequestBody UsuarioDTO usuarioDTO) {

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Usuario> optUsuario = this.usuarioService.cambiarContrasenia(nombre, usuarioDTO);
            UsuarioDTO UsuarioDTO = UsuarioMapper.entityToDtoCambioContrasenia(optUsuario.get());
            response.put("success", Boolean.TRUE);
            response.put("message", "La contraseña se actualizo con Exito");
            return ResponseEntity.ok(response);

        }catch (UsuarioException ue) {

            response.put("success", Boolean.FALSE);
            response.put("message", "La contraseña no se actualizo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
    }
}
