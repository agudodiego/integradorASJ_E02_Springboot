package com.asjservicios.seriesappspringboot.service.serviceImpl;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SerieService serieService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, SerieService serieService) {
        this.usuarioRepository = usuarioRepository;
        this.serieService = serieService;
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (this.usuarioExist(usuario.getUsuario())) {
            return null;
        }
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return this.usuarioRepository.findByUsuario(nombre);
    }

    @Override
    public UsuarioDTO validarUsuario(String nombre, Usuario usuario) {
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
        return null;
    }

    // METODOS AUXILIARES **************************************************
    @Override
    public boolean usuarioExist(String nombre) {
        return this.usuarioRepository.findByUsuario(nombre).isPresent();
    }

}
