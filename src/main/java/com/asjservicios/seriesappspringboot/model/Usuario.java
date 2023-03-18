package com.asjservicios.seriesappspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @Column(unique = true, nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String contrasenia;
    @Column(nullable = false)
    private String email;

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // usuario es el atributo (de tipo Usuario) en la clase UsuarioSerie
    private Collection<UsuarioSerie> usuarioSeries;


}
