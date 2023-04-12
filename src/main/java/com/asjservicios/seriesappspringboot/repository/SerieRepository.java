package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.model.DTOs.SerieDTO;
import com.asjservicios.seriesappspringboot.model.Serie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Integer> {

    @Query("from Serie s where s.id_serie = ?1")
    Optional<Serie> getSerieById(Integer id);
}
