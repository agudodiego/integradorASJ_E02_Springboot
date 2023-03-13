package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.model.SerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.repository.UsuarioSerieRepository;
import com.asjservicios.seriesappspringboot.service.UsuarioSerieService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioSerieServiceImpl implements UsuarioSerieService {

    private final UsuarioSerieRepository usuarioSerieRepository;

    public UsuarioSerieServiceImpl(UsuarioSerieRepository usuarioSerieRepository) {
        this.usuarioSerieRepository = usuarioSerieRepository;
    }

    @Override
    public Optional<UsuarioSerie> findById(Integer id) {
        Optional<UsuarioSerie> optUsuSerie = this.usuarioSerieRepository.findById(id);
        return optUsuSerie;
    }

    @Override
    public Optional<UsuarioSerie> updateRelacion(UsuarioSerie relacionFront) {
        Integer id_usuario = relacionFront.getUsuario().getId_usuario();
        Integer id_serie = relacionFront.getSerie().getId_serie();

        System.out.println("--------------------> " + id_usuario);
        System.out.println("--------------------> " + id_serie);
        Optional<UsuarioSerie> optRelacion = this.usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(id_usuario, id_serie);
        System.out.println("--------------------> " + optRelacion);

        if (optRelacion.isPresent()) {
            optRelacion.get().setTemp_actual(relacionFront.getTemp_actual());
            optRelacion.get().setEpisod_actual(relacionFront.getEpisod_actual());
            optRelacion.get().setActiva(relacionFront.getActiva());
            optRelacion.get().setPlataforma(relacionFront.getPlataforma());
            this.usuarioSerieRepository.save(optRelacion.get());
            return optRelacion;
        }
        return Optional.empty();
    }
}
