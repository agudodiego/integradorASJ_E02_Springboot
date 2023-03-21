package com.asjservicios.seriesappspringboot.datos;

import com.asjservicios.seriesappspringboot.model.Plataforma;

public class PlataformaDummy {

    public static Plataforma getSinPlataforma() {
        Plataforma plataforma = new Plataforma();
        plataforma.setId_plataforma(1);
        plataforma.setPlataforma("Sin Plataforma");
        plataforma.setUrl(null);
        return plataforma;
    }

    public static Plataforma getCuevana() {
        Plataforma plataforma = new Plataforma();
        plataforma.setId_plataforma(2);
        plataforma.setPlataforma("Cuevana");
        plataforma.setUrl("https://cuevana.com");
        return plataforma;
    }
}
