package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.mapper.SerieMapper;
import com.asjservicios.seriesappspringboot.model.*;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.repository.GeneroRepository;
import com.asjservicios.seriesappspringboot.repository.SerieRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioSerieRepository;
import com.asjservicios.seriesappspringboot.service.SerieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {

    private final SerieRepository serieRepository;
    private final GeneroRepository generoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioSerieRepository usuarioSerieRepository;

    public SerieServiceImpl(SerieRepository serieRepository, GeneroRepository generoRepository, UsuarioRepository usuarioRepository, UsuarioSerieRepository usuarioSerieRepository) {
        this.serieRepository = serieRepository;
        this.generoRepository = generoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioSerieRepository = usuarioSerieRepository;
    }

    @Override
    public Optional<Serie> findById(Integer id) {
        Optional<Serie> optSerie = this.serieRepository.findById(id);
        return optSerie;
    }

    @Override
    public SerieDTO save(String nombreUsuario, SerieDTO serieDTO) {
        Optional<Usuario> optUsuario = this.usuarioRepository.buscarPorNombre(nombreUsuario);
        Optional<Serie> optSerie = this.findById(serieDTO.getId_serie());

        if (optUsuario.isPresent()) {

            if (optSerie.isEmpty()) {
                System.out.println("--------------->> creo la serie");
                List<Genero> generos = (List<Genero>) this.generoRepository.findAll();
                // Mapeo la nueva serie a traves de SerieMapper
                Serie serieNueva = SerieMapper.dtoToEntity(serieDTO, generos);

                //Creacion de la relacion entre el usuario y la serie
                UsuarioSerie relacion = this.crearRelacion(optUsuario.get(), serieNueva, serieDTO );
                this.usuarioSerieRepository.save(relacion);

                return serieDTO;
            } else {
                Optional<UsuarioSerie> optRelacion = this.usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(optUsuario.get().getId_usuario(), optSerie.get().getId_serie());

                if (optRelacion.isPresent()) {
                    System.out.println("--------------->> actualizo la relacion xq el usuario ya la tiene en su repositorio");
                    //optRelacion.get().setUsuario(optUsuario.get()); // chequear esto
                    //optRelacion.get().setSerie(optSerie.get()); // chequear esto

                    optRelacion.get().setActiva(true);
                    this.usuarioSerieRepository.save(optRelacion.get());

                } else {
                    System.out.println("--------------->> creo la relacion con el usuario xq la serie ya esta en la BD");
                    UsuarioSerie nuevaRelacion = this.crearRelacion(optUsuario.get(), optSerie.get(), serieDTO );
                    this.usuarioSerieRepository.save(nuevaRelacion);
                }
                return serieDTO;
            }
        }
        return null;
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

//    private List<Genero> crearListadoDeGeneros(SerieDTO serieDTO, List<Genero> generos) {
//        String[] generosDsdFront = serieDTO.getGenero();
//        List<Genero> listadoGenerosParaSerie = new ArrayList<>();
//        for (String genero: generosDsdFront) { // array de generos (string) que viene dsd el front
//            Boolean esta = false;
//            Genero generoEnBD = new Genero();
//            for (Genero g: generos) { // array generos que vienen de la BD
//                if (genero.equals(g.getGenero())) {
//                    esta = true;
//                    generoEnBD = g;
//                    break;
//                }
//            }
//            if (esta) {
//                listadoGenerosParaSerie.add(generoEnBD);
//            } else {
//                Genero generoNuevo = new Genero();
//                generoNuevo.setGenero(genero);
//                listadoGenerosParaSerie.add(generoNuevo);
//            }
//        }
//        return listadoGenerosParaSerie;
//    }

}
