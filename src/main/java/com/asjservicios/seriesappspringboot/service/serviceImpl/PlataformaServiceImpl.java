package com.asjservicios.seriesappspringboot.service.serviceImpl;

import com.asjservicios.seriesappspringboot.exceptions.PlataformaException;
import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.repository.PlataformaRepository;
import com.asjservicios.seriesappspringboot.service.PlataformaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    private final PlataformaRepository plataformaRepository;

    public PlataformaServiceImpl(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    @Override
    public List<Plataforma> findAll() throws PlataformaException {

        List<Plataforma> plataformas = (List<Plataforma>) this.plataformaRepository.findAll();
        if (plataformas != null) {
            return plataformas;
        }

        throw new PlataformaException();
    }
}
