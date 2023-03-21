package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.datos.UsuarioDummy;
import com.asjservicios.seriesappspringboot.model.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {

    String usuarioTest = "Mario";
    String contraseniaTest = "mgomez12345";
    String usuarioInexistenteTest = "Pedro";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("[repository] - Buscar por nombre de usuario")
    void findByUsuario() {
        // GIVEN
        this.usuarioRepository.save(UsuarioDummy.getUsuarioMario());
        this.usuarioRepository.save(UsuarioDummy.getUsuarioDiego());

        // WHEN
        Optional<Usuario> optUsuario = this.usuarioRepository.findByUsuario(usuarioTest);
        Optional<Usuario> optUsuarioInexistente = this.usuarioRepository.findByUsuario(usuarioInexistenteTest);

        // THEN
        assertThat(optUsuario.isPresent())
                .isTrue();

        assertThat(optUsuario.get().getUsuario())
                .isEqualTo(usuarioTest);

        assertThat(optUsuario.get().getContrasenia()).isEqualTo(contraseniaTest);

        assertThat(optUsuarioInexistente.isPresent())
                .isFalse();
    }
}