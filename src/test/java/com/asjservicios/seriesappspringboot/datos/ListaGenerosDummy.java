package com.asjservicios.seriesappspringboot.datos;

import com.asjservicios.seriesappspringboot.model.Genero;

import java.util.ArrayList;
import java.util.List;

public class ListaGenerosDummy {

    public static List<Genero> getListaDeGeneros() {
        List<Genero> generos = new ArrayList<>();

        Genero genero1 = new Genero();
//        genero1.setId_genero(1);
        genero1.setGenero("Comedia");

        Genero genero2 = new Genero();
//        genero1.setId_genero(2);
        genero1.setGenero("Drama");

        generos.add(genero1);
        generos.add(genero2);

        return generos;
    }
}
