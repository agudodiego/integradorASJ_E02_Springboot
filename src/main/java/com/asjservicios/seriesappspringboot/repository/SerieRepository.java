package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.model.Serie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Integer> {
}
