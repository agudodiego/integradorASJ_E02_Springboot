package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
import com.asjservicios.seriesappspringboot.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (this.usuarioExist(usuario.getUsuario())) {
            return null;
//            throw new RuntimeException( String.format("El usuario con nombre %s ya existe", usuario.getUsuario()) );
        }
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return this.usuarioRepository.buscarPorNombre(nombre);
    }

    @Override
    public Optional<Usuario> validarUsuario(String nombre, Usuario usuario) {
        Optional<Usuario> optUsuario = this.buscarPorNombre(nombre);
        if (optUsuario.isPresent() && usuario.getUsuario().equals(optUsuario.get().getUsuario()) && usuario.getContrasenia().equals(optUsuario.get().getContrasenia())) {
            return optUsuario;
        }
        return Optional.empty();
    }

    // METODOS AUXILIARES **************************************************
    @Override
    public boolean usuarioExist(String nombre) {
        return this.usuarioRepository.buscarPorNombre(nombre).isPresent();
    }

}
