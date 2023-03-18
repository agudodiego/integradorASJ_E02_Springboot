package com.asjservicios.seriesappspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "series")
public class Serie {

    @Id
    @Column(unique = true, nullable = false)
    private Integer id_serie;
    private String titulo;
    private Integer temporadas;
    private Integer episodios;
    private String img_small;
    private String img_big;
    private String anio_lanzamiento;
    private String sitio_oficial;
    private String descripcion;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "series_generos",
            joinColumns = @JoinColumn(name = "id_serie"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private List<Genero> generos = new ArrayList<Genero>();

    @OneToMany (mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // serie es el atributo (de tipo Serie) en la clase UsuarioSerie
    private Collection<UsuarioSerie> usuarioSeries;


}
