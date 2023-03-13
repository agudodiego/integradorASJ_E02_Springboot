package com.asjservicios.seriesappspringboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "plataformas")
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plataforma;
    private String plataforma;
    private String url;

}
