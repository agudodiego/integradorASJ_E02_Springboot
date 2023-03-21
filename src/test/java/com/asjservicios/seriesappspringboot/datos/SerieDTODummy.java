package com.asjservicios.seriesappspringboot.datos;

import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;

public class SerieDTODummy {

    public static SerieDTO getFriendsDTO() {
        String[] generos = {"Comedia", "Drama"};
        SerieDTO friensdDto = new SerieDTO();
        friensdDto.setId_serie(20);
        friensdDto.setTitulo("Friends");
        friensdDto.setTemporadas(10);
        friensdDto.setEpisodios(380);
        friensdDto.setImg_small("url a imagen pequeña");
        friensdDto.setImg_big("url a imagen grande");
        friensdDto.setAnio_lanzamiento("1990");
        friensdDto.setSitio_oficial("https://friends.com");
        friensdDto.setDescripcion("bla bla bla bla bla bla bla");
        friensdDto.setGenero(generos);
        friensdDto.setPlataforma(PlataformaDummy.getCuevana());
        friensdDto.setActiva(true);
        friensdDto.setTemp_actual(1);
        friensdDto.setEpisod_actual(18);
        friensdDto.setEpisod_actual(1);
        return friensdDto;
    }
}
