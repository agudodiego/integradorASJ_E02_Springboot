package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.RelacionException;
import com.asjservicios.seriesappspringboot.mapper.UsuarioSerieMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.UsuarioSerieDTO;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.repository.UsuarioSerieRepository;
import com.asjservicios.seriesappspringboot.service.UsuarioSerieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor // Annotation (de Lombok) para eliminar los constructores
@Service
public class UsuarioSerieServiceImpl implements UsuarioSerieService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioSerieServiceImpl.class);

    private final UsuarioSerieRepository usuarioSerieRepository;

    @Override
    public UsuarioSerieDTO findById(Integer id) throws RelacionException {
        Optional<UsuarioSerie> optUsuSerie = usuarioSerieRepository.findById(id);
        if (optUsuSerie.isPresent()) {
            return UsuarioSerieMapper.entityToDto(optUsuSerie.get());
        }
        logger.warn("No existe ninguna relacion entre usuario/serie con el ID: "+ id);
        throw new RelacionException("La relacion no existe", HttpStatus.NOT_FOUND);
    }

    @Override
    public UsuarioSerieDTO updateRelacion(UsuarioSerieDTO relacionFrontDTO) throws RelacionException {
        Integer id_usuario = relacionFrontDTO.getId_usuario();
        Integer id_serie = relacionFrontDTO.getId_serie();

        Optional<UsuarioSerie> optRelacion = usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(id_usuario, id_serie);

        if (optRelacion.isPresent()) {
            optRelacion.get().setTemp_actual(relacionFrontDTO.getTemp_actual());
            optRelacion.get().setEpisod_actual(relacionFrontDTO.getEpisod_actual());
            optRelacion.get().setActiva(relacionFrontDTO.getActiva());
            optRelacion.get().setPlataforma(relacionFrontDTO.getPlataforma());
            usuarioSerieRepository.save(optRelacion.get());
            return relacionFrontDTO;
        }
        logger.warn("No existe ningune relacion entre el usuario con id: " + id_usuario + " y la serie con id: "+ id_serie);
        throw new RelacionException("La relacion entre el usuario y la serie no existe", HttpStatus.CONFLICT);
    }
}
