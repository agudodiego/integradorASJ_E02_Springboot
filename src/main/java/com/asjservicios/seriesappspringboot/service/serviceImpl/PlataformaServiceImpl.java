package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.PlataformaException;
import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.repository.PlataformaRepository;
import com.asjservicios.seriesappspringboot.service.PlataformaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    private static final Logger logger = LoggerFactory.getLogger(PlataformaServiceImpl.class);

    private final PlataformaRepository plataformaRepository;

    public PlataformaServiceImpl(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    @Override
    public List<Plataforma> findAll() throws NoSuchElementException {

        List<Plataforma> plataformas = (List<Plataforma>) this.plataformaRepository.findAll();
        if (plataformas != null) {
            return plataformas;
        }
        logger.warn("Las plataformas no fueron cargadas en la base de datos. Deberan CARGARSE para un correcto funcionamiento de la pagina");
        throw new NoSuchElementException("Usuario y/o contraseña incorrecto/s");
    }
}
