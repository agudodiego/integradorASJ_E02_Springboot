package com.asjservicios.seriesappspringboot.datos;

import com.asjservicios.seriesappspringboot.model.*;

import java.util.ArrayList;
import java.util.List;

public class RelacionDummy {

    public static UsuarioSerie getRelacionDiegoYFriends() {

        // Creo la relacion entre el usuario y la serie
        UsuarioSerie relacion = new UsuarioSerie();
        relacion.setId_usuario_serie(1);
        relacion.setUsuario(UsuarioDummy.getUsuarioDiegoConId());
        relacion.setSerie(SerieDummy.getSerieFriends());
        relacion.setTemp_actual(1);
        relacion.setEpisod_actual(13);
        relacion.setActiva(true);
        relacion.setPlataforma(PlataformaDummy.getCuevana());

        return relacion;
    }
}
