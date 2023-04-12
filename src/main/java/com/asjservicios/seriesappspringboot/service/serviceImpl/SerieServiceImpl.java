package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.SerieException;
import com.asjservicios.seriesappspringboot.mapper.SerieMapper;
import com.asjservicios.seriesappspringboot.model.*;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.repository.GeneroRepository;
import com.asjservicios.seriesappspringboot.repository.SerieRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioSerieRepository;
import com.asjservicios.seriesappspringboot.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // Annotation (de Lombok) para eliminar los constructores
@Service
public class SerieServiceImpl implements SerieService {

    private static final Logger logger = LoggerFactory.getLogger(SerieServiceImpl.class);

    private final SerieRepository serieRepository;
    private final GeneroRepository generoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioSerieRepository usuarioSerieRepository;

    @Override
    public SerieDTO getSerieById(Integer id) {
        return SerieMapper.entityToDtoSinRelacion(serieRepository.getSerieById(id).get());
    }

    @Override
    public Optional<Serie> findById(Integer id) {
        Optional<Serie> optSerie = serieRepository.findById(id);
        return optSerie;
    }

    @Override
    public SerieDTO save(String nombreUsuario, SerieDTO serieDTO) throws SerieException {
        Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(nombreUsuario);
        Optional<Serie> optSerie = this.findById(serieDTO.getId_serie());

        if (optUsuario.isPresent()) {

            if (optSerie.isEmpty()) {
                logger.info("--------------->> creo la serie: " + serieDTO.getTitulo());
                List<Genero> generos = (List<Genero>) generoRepository.findAll();
                // Mapeo la nueva serie a traves de SerieMapper
                Serie serieNueva = SerieMapper.dtoToEntity(serieDTO, generos);

                //Creacion de la relacion entre el usuario y la serie
                UsuarioSerie relacion = this.crearRelacion(optUsuario.get(), serieNueva, serieDTO );
                usuarioSerieRepository.save(relacion);

                return serieDTO;
            } else {
                Optional<UsuarioSerie> optRelacion = usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(optUsuario.get().getId_usuario(), optSerie.get().getId_serie());

                if (optRelacion.isPresent()) {
                    logger.info("--------------->> actualizo la relacion xq el usuario "+ optUsuario.get().getUsuario() +" ya tiene la serie " + optSerie.get().getTitulo() + " en su repositorio");
                    // cargo los datos de la relacion en el objeto serie que devuelve la API
                    serieDTO.setEpisod_actual(optRelacion.get().getEpisod_actual());
                    serieDTO.setTemp_actual(optRelacion.get().getTemp_actual());
                    serieDTO.setPlataforma(optRelacion.get().getPlataforma());
                    optRelacion.get().setActiva(true);
                    usuarioSerieRepository.save(optRelacion.get());

                } else {
                    logger.info("--------------->> creo la relacion con el usuario "+ optUsuario.get().getUsuario() +" xq la serie "+ optSerie.get().getTitulo() +" ya esta en la BD");
                    UsuarioSerie nuevaRelacion = this.crearRelacion(optUsuario.get(), optSerie.get(), serieDTO );
                    usuarioSerieRepository.save(nuevaRelacion);
                }
                return serieDTO;
            }
        }
        logger.warn("El usuario "+ nombreUsuario + " no existe");
        throw new SerieException("La serie "+ serieDTO.getTitulo() +" no pudo ser creada");
    }

    private UsuarioSerie crearRelacion(Usuario usuario, Serie serie, SerieDTO serieDTO) {
        UsuarioSerie relacion = new UsuarioSerie();
        relacion.setUsuario(usuario);
        relacion.setSerie(serie);
        relacion.setTemp_actual(serieDTO.getTemp_actual());
        relacion.setEpisod_actual(serieDTO.getEpisod_actual());
        relacion.setActiva(true);
        relacion.setPlataforma(serieDTO.getPlataforma());
        return relacion;
    }
}
