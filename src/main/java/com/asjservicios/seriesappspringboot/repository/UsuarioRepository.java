package com.asjservicios.seriesappspringboot.repository;

import com.asjservicios.seriesappspringboot.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    //SELECT * FROM usuarios WHERE usuario = ?
    @Query("from Usuario u where u.usuario = ?1")
    Optional<Usuario> buscarPorNombre(String nombre);
}
