package com.asjservicios.seriesappspringboot.config;

import com.asjservicios.seriesappspringboot.model.Plataforma;
import com.asjservicios.seriesappspringboot.repository.PlataformaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DataSeeder {

    private final PlataformaRepository plataformaRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {

        if (!plataformaRepository.existsById(1)) {
//            Plataforma pl1 = new Plataforma();
//            pl1.setPlataforma("Cuevana");
//            pl1.setUrl("https://www4.cuevana3.ch/");
            plataformaRepository.save(new Plataforma("Sin Plataforma", ""));
            plataformaRepository.save(new Plataforma("cuevana", "https://www4.cuevana3.ch/"));
            plataformaRepository.save(new Plataforma("pelispedia", "https://pelispedia.one"));
            plataformaRepository.save(new Plataforma("seriesw", "https://mindandfist.com/"));
            plataformaRepository.save(new Plataforma("plutoTV", "https://pluto.tv/"));
        }
    }
}
