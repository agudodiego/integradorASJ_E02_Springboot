package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.datos.RelacionDummy;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioSerieRepositoryTest {

    @Autowired
    private UsuarioSerieRepository usuarioSerieRepository;

    @Test
    @DisplayName("[repository] - Buscar por ID de usuario e ID de serie")
    void buscarPorIdUsuarioEIdSerie() {

        Integer testIdUsuario = 1;
        String testNombreUsuario = "Diego";
        Integer testIdSerie = 20;
        String testTituloSerie = "Friends";

        //GIVEN (genero el contexto para poder usar el metodo a testear)
        this.usuarioSerieRepository.save(RelacionDummy.getRelacionDiegoYFriends());

        //WHEN (pruebo el metodo en cuestion)
        Optional<UsuarioSerie> optRelacion = this.usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(testIdUsuario, testIdSerie);

        //THEN
        assertThat(optRelacion.isPresent())
                .isTrue();

        assertThat(optRelacion.get().getUsuario().getUsuario())
                .isEqualTo(testNombreUsuario);

        assertThat(optRelacion.get().getSerie().getTitulo())
                .isEqualTo(testTituloSerie);

        assertThat(optRelacion.get().getActiva()).isTrue();

    }
}