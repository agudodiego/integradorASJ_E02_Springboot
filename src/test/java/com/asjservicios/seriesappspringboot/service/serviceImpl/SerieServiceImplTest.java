package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.datos.*;
import com.asjservicios.seriesappspringboot.exceptions.SerieException;
import com.asjservicios.seriesappspringboot.mapper.UsuarioSerieMapper;
import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.model.Serie;
import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import com.asjservicios.seriesappspringboot.repository.GeneroRepository;
import com.asjservicios.seriesappspringboot.repository.SerieRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioRepository;
import com.asjservicios.seriesappspringboot.repository.UsuarioSerieRepository;
import com.asjservicios.seriesappspringboot.service.SerieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class SerieServiceImplTest {

    String tituloTest = "Friends";
    String usuarioTest = "Diego";
    Integer idUsuarioTest = 1;
    Integer idSerieTest = 20;

    @Mock // dado que el repositorio solo usa los metodos de JPA no lo testeo sino que lo "Mockeo"
    private SerieRepository serieRepository;
    private SerieService serieService;
    private GeneroRepository generoRepository;
    private UsuarioRepository usuarioRepository;
    private UsuarioSerieRepository usuarioSerieRepository;

    @BeforeEach
    void setUp() {
        serieRepository = mock(SerieRepository.class);
        usuarioSerieRepository = mock(UsuarioSerieRepository.class);
        generoRepository = mock(GeneroRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
        serieService = new SerieServiceImpl(serieRepository, generoRepository, usuarioRepository, usuarioSerieRepository);
    }

    @Test
    @DisplayName("[Serie Service] - Buscar serie por ID")
    void findById() {
        // GIVEN como el contexto esta armado en el setUp() puedo omitir el "given"
        when(serieRepository.findById(20))
                .thenReturn(Optional.of(SerieDummy.getSerieFriends()));

        // WHEN
        Optional<Serie> optSerie = this.serieService.findById(20);

        // THEN
        assertThat(optSerie.isPresent())
                .isTrue();

        assertThat(optSerie.get().getTitulo())
                .isEqualTo(tituloTest);

        assertThat(optSerie.get().getGeneros().size()).isEqualTo(2);

        verify(serieRepository).findById(idSerieTest);
    }

    @Test
    @DisplayName("[Serie Service] - Actualizacion de la relacion (el usuario ya la tiene en su lista)")
    void save() throws SerieException{
        // GIVEN
        when(usuarioRepository.findByUsuario(usuarioTest))
                .thenReturn(Optional.of(UsuarioDummy.getUsuarioDiegoConId()));

        when(serieRepository.findById(20))
                .thenReturn(Optional.of(SerieDummy.getSerieFriends()));

        when(usuarioSerieRepository.save(RelacionDummy.getRelacionDiegoYFriends()))
                .thenReturn(RelacionDummy.getRelacionDiegoYFriends());

        when(usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(idUsuarioTest, idSerieTest))
                .thenReturn(Optional.of(RelacionDummy.getRelacionDiegoYFriends()));

        SerieDTO sDTO = SerieDTODummy.getFriendsDTO();

        // WHEN
        SerieDTO serieDTO = this.serieService.save(usuarioTest, sDTO);

        // THEN
        assertThat(serieDTO != null).isTrue();

        verify(usuarioRepository).findByUsuario(usuarioTest);

        verify(usuarioSerieRepository).buscarPorIdUsuarioEIdSerie(idUsuarioTest, idSerieTest);

        verify(usuarioSerieRepository).save(RelacionDummy.getRelacionDiegoYFriends());

    }

    @Test
    @DisplayName("[Serie Service] - Guardar serie que no esta en BD")
    void saveSerieAusenteBD() throws SerieException {

        // GIVEN
        when(usuarioRepository.findByUsuario(usuarioTest))
                .thenReturn(Optional.of(UsuarioDummy.getUsuarioDiegoConId()));

        when(serieRepository.findById(20))
                .thenReturn(Optional.empty());

        SerieDTO sDTO = SerieDTODummy.getFriendsDTO();

        // WHEN
        SerieDTO serieDTO = this.serieService.save(usuarioTest, sDTO);

        // THEN
        assertThat(serieDTO != null).isTrue();

        assertThat(serieDTO.getId_serie() == idSerieTest).isTrue();

        verify(usuarioRepository).findByUsuario(usuarioTest);

    }

    @Test
    @DisplayName("[Serie Service] - Creacion la relacion con el usuario (La serie ya esta en la BD)")
    void saveRelacionAusenteBD() throws SerieException {

        // GIVEN
        when(usuarioRepository.findByUsuario(usuarioTest))
                .thenReturn(Optional.of(UsuarioDummy.getUsuarioDiegoConId()));

        when(serieRepository.findById(20))
                .thenReturn(Optional.of(SerieDummy.getSerieFriends()));

        when(usuarioSerieRepository.buscarPorIdUsuarioEIdSerie(idUsuarioTest, idSerieTest))
                .thenReturn(Optional.empty());

        SerieDTO sDTO = SerieDTODummy.getFriendsDTO();

        // WHEN
        SerieDTO serieDTO = this.serieService.save(usuarioTest, sDTO);

        // THEN
        assertThat(serieDTO != null).isTrue();

        assertThat(serieDTO.getId_serie() == idSerieTest).isTrue();

        //verify(usuarioSerieRepository).save(RelacionDummy.getRelacionDiegoYFriends());

    }

    @Test
    @DisplayName("[Serie Service] - Salida por la excepcion")
    void saveConException() throws SerieException {

        // GIVEN
        when(usuarioRepository.findByUsuario(usuarioTest))
                .thenReturn(Optional.empty());

        when(serieRepository.findById(20))
                .thenReturn(Optional.of(SerieDummy.getSerieFriends()));

        SerieDTO sDTO = SerieDTODummy.getFriendsDTO();

        // WHEN

        // THEN
        assertThatThrownBy(() -> this.serieService.save(usuarioTest, sDTO))
                .isInstanceOf(SerieException.class);

    }
}