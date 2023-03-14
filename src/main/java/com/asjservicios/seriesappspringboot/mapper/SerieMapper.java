package com.asjservicios.seriesappspringboot.mapper;

import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.model.Genero;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SerieMapper {

    public static Serie dtoToEntity (SerieDTO dto, List<Genero> generosEnBD) {
        Serie entity = new Serie();
        entity.setId_serie(dto.getId_serie());
        entity.setTitulo(dto.getTitulo());
        entity.setTemporadas(dto.getTemporadas());
        entity.setEpisodios(dto.getEpisodios());
        entity.setImg_small(dto.getImg_small());
        entity.setImg_big(dto.getImg_big());
        entity.setAnio_lanzamiento(dto.getAnio_lanzamiento());
        entity.setSitio_oficial(dto.getSitio_oficial());
        entity.setDescripcion(dto.getDescripcion());
        entity.setGeneros(crearListadoDeGeneros(dto.getGenero(), generosEnBD));
        return entity;
    }

    public static SerieDTO entityToDto(Serie entity, UsuarioSerie relacion) {
        SerieDTO dto = new SerieDTO();
        dto.setId_serie(entity.getId_serie());
        dto.setTitulo(entity.getTitulo());
        dto.setTemporadas(entity.getTemporadas());
        dto.setEpisodios(entity.getEpisodios());
        dto.setImg_small(entity.getImg_small());
        dto.setImg_big(entity.getImg_big());
        dto.setAnio_lanzamiento(entity.getAnio_lanzamiento());
        dto.setSitio_oficial(entity.getSitio_oficial());
        dto.setDescripcion(entity.getDescripcion());
        dto.setGenero(pasarGenerosAString(entity.getGeneros()));
        dto.setPlataforma(relacion.getPlataforma());
        dto.setActiva(relacion.getActiva());
        dto.setTemp_actual(relacion.getTemp_actual());
        dto.setEpisod_actual(relacion.getEpisod_actual());
        dto.setId_relacion(relacion.getId_usuario_serie());
        return dto;
    }

    public static SerieDTO entityToDtoSinRelacion(Serie entity) {
        SerieDTO dto = new SerieDTO();
        dto.setId_serie(entity.getId_serie());
        dto.setTitulo(entity.getTitulo());
        dto.setTemporadas(entity.getTemporadas());
        dto.setEpisodios(entity.getEpisodios());
        dto.setImg_small(entity.getImg_small());
        dto.setImg_big(entity.getImg_big());
        dto.setAnio_lanzamiento(entity.getAnio_lanzamiento());
        dto.setSitio_oficial(entity.getSitio_oficial());
        dto.setDescripcion(entity.getDescripcion());
        dto.setGenero(pasarGenerosAString(entity.getGeneros()));
        return dto;
    }

    private static List<Genero> crearListadoDeGeneros(String[] generosDsdFront, List<Genero> generos) {

        List<Genero> listadoGenerosParaSerie = new ArrayList<>();

        for (String genero: generosDsdFront) { // array de generos (string) que viene dsd el front
            Boolean esta = false;
            Genero generoEnBD = new Genero();
            for (Genero g: generos) { // array generos que vienen de la BD
                if (genero.equals(g.getGenero())) {
                    esta = true;
                    generoEnBD = g;
                    break;
                }
            }
            if (esta) {
                listadoGenerosParaSerie.add(generoEnBD);
            } else {
                Genero generoNuevo = new Genero();
                generoNuevo.setGenero(genero);
                listadoGenerosParaSerie.add(generoNuevo);
            }
        }
        return listadoGenerosParaSerie;
    }

    private static String[] pasarGenerosAString(Collection<Genero> generosObj) {
        String[] generosDeLaSerie = new String[generosObj.size()];
        int indice = 0;
        for (Genero g: generosObj) {
            generosDeLaSerie[indice] = g.getGenero();
            indice++;
        }
        return generosDeLaSerie;
    }
}