package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
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
    public Optional<UsuarioSerie> findById(Integer id) throws RelacionException {
        Optional<UsuarioSerie> optUsuSerie = this.usuarioSerieRepository.findById(id);
        if (optUsuSerie.isPresent()) {
            return optUsuSerie;
        }
        throw new RelacionException();
    }

    @Override
    public UsuarioSerieDTO updateRelacion(UsuarioSerieDTO relacionFrontDTO) throws RelacionException {
        Integer id_usuario = relacionFrontDTO.getId_usuario();
        Integer id_serie = relacionFrontDTO.getId_serie();

        Optional<UsuarioSerie> optRelacion = this.usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(id_usuario, id_serie);

        if (optRelacion.isPresent()) {
            optRelacion.get().setTemp_actual(relacionFrontDTO.getTemp_actual());
            optRelacion.get().setEpisod_actual(relacionFrontDTO.getEpisod_actual());
            optRelacion.get().setActiva(relacionFrontDTO.getActiva());
            optRelacion.get().setPlataforma(relacionFrontDTO.getPlataforma());
            this.usuarioSerieRepository.save(optRelacion.get());
            return relacionFrontDTO;
        }
        throw new RelacionException();
    }
}
