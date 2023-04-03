package com.asjservicios.seriesappspringboot.controller;

import com.asjservicios.seriesappspringboot.exceptions.UsuarioException;
import com.asjservicios.seriesappspringboot.mapper.UsuarioMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioDTO;
import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.service.UsuarioService;
import com.asjservicios.seriesappspringboot.service.serviceImpl.UsuarioServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/{nombre}")
    @ApiOperation("Devuelve un Usuario con su lista de Series")
    public ResponseEntity<?> getUsuarioByName(@PathVariable String nombre, @RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();

        try {
            UsuarioDTO UsuarioDTO = this.usuarioService.traerUsuarioDTOCompleto(nombre, usuario);
            return ResponseEntity.ok(UsuarioDTO);

        } catch (UsuarioException ue) {

            response.put("success", Boolean.FALSE);
            response.put("message", "Usuario y/o contraseña incorrecto/s");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (NoSuchElementException nsee) {

            logger.warn("Se intento acceder con un usuario inexistente en la BD ("+nombre+")");
            response.put("success", Boolean.FALSE);
            response.put("message", "El Usuario no existe en la base de datos, debe registrarlo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @PostMapping("")
    @ApiOperation("Agrega un nuevo Usuario")
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
    @ApiOperation("Actualiza la contraseña de un usuario")
    public ResponseEntity updateContrasenia(@PathVariable String nombre, @Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {

        if(result.hasErrors()){
            Map<String, String> validaciones = new HashMap<>();
            result.getFieldErrors().forEach(v -> validaciones.put(v.getField(), v.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Usuario> optUsuario = this.usuarioService.cambiarContrasenia(nombre, usuarioDTO);
            UsuarioDTO UsuarioDTO = UsuarioMapper.entityToDtoCambioContrasenia(optUsuario.get());
            response.put("success", Boolean.TRUE);
            response.put("message", "La contraseña se actualizo con Exito");
            return ResponseEntity.ok(response);

        } catch (UsuarioException ue) {

            response.put("success", Boolean.FALSE);
            response.put("message", "La contraseña no se actualizo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
    }
}
