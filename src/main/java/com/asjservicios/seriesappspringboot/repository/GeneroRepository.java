package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.model.Genero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends CrudRepository<Genero, Integer> {
}
