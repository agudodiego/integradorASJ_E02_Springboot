package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.exceptions.UsuarioException;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioDTO;
import com.asjservicios.seriesappspringboot.model.Usuario;

import java.util.Map;
import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario usuario) throws UsuarioException;
    boolean usuarioExist(String nombre);
    Optional<Usuario> buscarPorNombre(String nombre);
    UsuarioDTO traerUsuarioDTOCompleto(String nombre, Usuario usuario) throws UsuarioException;
    Usuario cambiarContrasenia(String nombre, UsuarioDTO usuarioDTO) throws UsuarioException;

}
