package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioDTO;
import com.asjservicios.seriesappspringboot.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario usuario);
    boolean usuarioExist(String nombre);
    Optional<Usuario> buscarPorNombre(String nombre);
    UsuarioDTO validarUsuario(String nombre, Usuario usuario);
}
