package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.model.Plataforma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaRepository extends CrudRepository<Plataforma, Integer> {
}
