package com.asjservicios.seriesappspringboot.service;

import com.asjservicios.seriesappspringboot.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario save(Usuario usuario);
    boolean usuarioExist(String nombre);
    Optional<Usuario> buscarPorNombre(String nombre);
    Optional<Usuario> validarUsuario(String nombre, Usuario usuario);
}
