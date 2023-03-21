package com.asjservicios.seriesappspringboot.datos;

import com.asjservicios.seriesappspringboot.model.Genero;
import com.asjservicios.seriesappspringboot.model.Serie;

import java.util.ArrayList;
import java.util.List;

public class SerieDummy {

    public static Serie getSerieFriends() {
        Serie s = new Serie();
        s.setId_serie(20);
        s.setTitulo("Friends");
        s.setTemporadas(10);
        s.setEpisodios(380);
        s.setImg_small("url a imagen pequeña");
        s.setImg_big("url a imagen grande");
        s.setAnio_lanzamiento("1990");
        s.setSitio_oficial("https://friends.com");
        s.setDescripcion("bla bla bla bla bla bla bla");
        s.setGeneros(ListaGenerosDummy.getListaDeGeneros());
        return s;
    }

    public static Serie getSerieRambo() {
        Serie s = new Serie();
        s.setId_serie(21);
        s.setTitulo("Rambo");
        s.setTemporadas(3);
        s.setEpisodios(100);
        s.setImg_small("url a imagen pequeña");
        s.setImg_big("url a imagen grande");
        s.setAnio_lanzamiento("1984");
        s.setSitio_oficial("https://friends.com");
        s.setDescripcion("bla bla bla bla bla bla bla");
        s.setGeneros(ListaGenerosDummy.getListaDeGeneros());
        return s;
    }
}
