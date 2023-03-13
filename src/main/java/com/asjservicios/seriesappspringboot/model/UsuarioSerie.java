package com.asjservicios.seriesappspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuarios_series")
public class UsuarioSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario_serie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_serie")
    private Serie serie;

    // Campos adicionales
    private Integer temp_actual;
    private Integer episod_actual;
    private Boolean activa;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_plataforma")
    private Plataforma plataforma;

}
