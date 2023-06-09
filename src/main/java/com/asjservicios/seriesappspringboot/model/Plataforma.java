package com.asjservicios.seriesappspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plataformas")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plataforma;
    private String plataforma;
    private String url;

    public Plataforma(String plataforma, String url) {
        this.plataforma = plataforma;
        this.url = url;
    }
}
