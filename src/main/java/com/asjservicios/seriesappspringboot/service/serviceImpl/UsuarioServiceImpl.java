package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.UsuarioException;
import com.asjservicios.seriesappspringboot.mapper.SerieMapper;
import com.asjservicios.seriesappspringboot.mapper.UsuarioMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioDTO;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
import com.asjservicios.seriesappspringboot.service.SerieService;
import com.asjservicios.seriesappspringboot.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final SerieService serieService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SerieService serieService) {
        this.usuarioRepository = usuarioRepository;
        this.serieService = serieService;
    }

    @Override
    public Usuario save(Usuario usuario) throws UsuarioException {
        if (this.usuarioExist(usuario.getUsuario())) {
            logger.warn("Se intento crear al usuario " + usuario.getUsuario() +" pero ya esta en la base de datos");
            throw new UsuarioException("El suario " + usuario.getUsuario() +" ya existe", HttpStatus.CONFLICT);
        }
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return this.usuarioRepository.findByUsuario(nombre);
    }

    @Override
    public UsuarioDTO traerUsuarioDTOCompleto(String nombre, Usuario usuario) throws UsuarioException {
        Optional<Usuario> optUsuario = this.buscarPorNombre(nombre);
        List<SerieDTO> seriesDelUser = new ArrayList<>();
        if (optUsuario.isPresent() && usuario.getUsuario().equals(optUsuario.get().getUsuario()) && usuario.getContrasenia().equals(optUsuario.get().getContrasenia())) {
            // traigo las series del usuario y las mapeo
            optUsuario.get().getUsuarioSeries().stream()
                    .filter(UsuarioSerie::getActiva) // us -> us.getActiva() == true
                    .map( us -> {
                        Optional<Serie> optSerieAux = serieService.findById(us.getSerie().getId_serie());
                        return SerieMapper.entityToDto(optSerieAux.get(), us);
                    }).forEach(seriesDelUser::add); // s -> seriesDelUser.add(s)

            UsuarioDTO usuarioDTO = UsuarioMapper.entityToDto(optUsuario.get(), seriesDelUser);
            return usuarioDTO;
        }
        logger.warn("Las credenciales con las que se intenta acceder al usuario " + optUsuario.get().getUsuario() +" no son correctas");
        throw new UsuarioException("Usuario y/o contraseña incorrecto/s", HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<Usuario> cambiarContrasenia(String nombre, UsuarioDTO usuarioDTO) throws UsuarioException {

        Optional<Usuario> optUsuario = this.buscarPorNombre(nombre);

        if (optUsuario.isPresent() && usuarioDTO.getUsuario().equals(optUsuario.get().getUsuario()) && usuarioDTO.getContrasenia().equals(optUsuario.get().getContrasenia())) {
           optUsuario.get().setContrasenia(usuarioDTO.getNuevaContrasenia());
           this.usuarioRepository.save(optUsuario.get());
           return optUsuario;
        }
        logger.warn("Las credenciales con las que se intenta acceder al usuario " + optUsuario.get().getUsuario() +" para modificar la contraseña no son correctas");
        throw  new UsuarioException("La contraseña no se actualizo", HttpStatus.NOT_FOUND);
    }

    // METODOS AUXILIARES **************************************************
    @Override
    public boolean usuarioExist(String nombre) {
        return this.usuarioRepository.findByUsuario(nombre).isPresent();
    }

}
