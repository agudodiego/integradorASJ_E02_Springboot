package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioSerieRepository;
import com.asjservicios.seriesappspringboot.service.UsuarioSerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioSerieServiceImpl implements UsuarioSerieService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioSerieServiceImpl.class);

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
        logger.warn("No existe ninguna relacion entre usuario/serie con el ID: "+ id);
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
        logger.warn("No existe ningune relacion entre el usuario con id: " + id_usuario + " y la serie con id: "+ id_serie);
        throw new RelacionException();
    }
}
