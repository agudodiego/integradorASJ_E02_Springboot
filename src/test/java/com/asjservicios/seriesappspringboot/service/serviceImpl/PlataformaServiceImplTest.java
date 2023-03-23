package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.datos.PlataformaDummy;
import com.asjservicios.seriesappspringboot.exceptions.PlataformaException;
import com.asjservicios.seriesappspringboot.exceptions.SerieException;
import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.repository.PlataformaRepository;
import com.asjservicios.seriesappspringboot.service.PlataformaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlataformaServiceImplTest {

    List<Plataforma> plataformasConExc = null;

    @Mock
    private PlataformaRepository plataformaRepository;
    private PlataformaService plataformaService;

    @BeforeEach
    void setUp() {
        plataformaRepository = mock(PlataformaRepository.class);
        plataformaService = new PlataformaServiceImpl(plataformaRepository);
    }

    @Test
    @DisplayName("[Plataforma Service] - Traer todas las plataformas")
    void findAll() throws PlataformaException {
        // GIVEN
        when(plataformaRepository.findAll())
                .thenReturn(Arrays.asList(
                        PlataformaDummy.getSinPlataforma(),
                        PlataformaDummy.getCuevana()
                ));

        // WHEN
        List<Plataforma> plataformas = this.plataformaService.findAll();

        // THEN
        assertThat(plataformas).isNotEmpty();

        assertThat(plataformas.size()).isEqualTo(2);

        verify(plataformaRepository).findAll();

    }

    @Test
    @DisplayName("[Plataforma Service] - Salida por la excepcion")
    void findAllConException() throws PlataformaException {
        // GIVEN
        when(plataformaRepository.findAll())
                .thenReturn(plataformasConExc);

        // WHEN

        // THEN
        assertThatThrownBy(() -> this.plataformaService.findAll())
                .isInstanceOf(PlataformaException.class);

    }
}