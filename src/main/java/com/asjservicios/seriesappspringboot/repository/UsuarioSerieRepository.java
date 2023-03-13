package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.model.Usuario;
import com.asjservicios.seriesappspringboot.model.UsuarioSerie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSerieRepository extends CrudRepository<UsuarioSerie, Integer> {

    //SELECT * FROM usuarios_series WHERE id_usuario = ? AND id_serie = ?;
    @Query("from UsuarioSerie us where us.usuario.id_usuario = ?1 and us.serie.id_serie = ?2")
    Optional<UsuarioSerie> buscarPorIdUsuarioEIdSerie(Integer idUsuario, Integer IdSerie);
}
