package com.asjservicios.seriesappspringboot.model;

public class SerieDTO {
    private Integer id_serie;
    private String titulo;
    private Integer temporadas;
    private Integer episodios;
    private String img_small;
    private String img_big;
    private String anio_lanzamiento;
    private String sitio_oficial;
    private String descripcion;
    private String[] genero;
    private Plataforma plataforma;
    private Boolean activa;
    private Integer temp_actual;
    private Integer episod_actual;

    public SerieDTO() {
    }

    public Integer getId_serie() {
        return id_serie;
    }

    public void setId_serie(Integer id_serie) {
        this.id_serie = id_serie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Integer temporadas) {
        this.temporadas = temporadas;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public String getImg_small() {
        return img_small;
    }

    public void setImg_small(String img_small) {
        this.img_small = img_small;
    }

    public String getImg_big() {
        return img_big;
    }

    public void setImg_big(String img_big) {
        this.img_big = img_big;
    }

    public String getAnio_lanzamiento() {
        return anio_lanzamiento;
    }

    public void setAnio_lanzamiento(String anio_lanzamiento) {
        this.anio_lanzamiento = anio_lanzamiento;
    }

    public String getSitio_oficial() {
        return sitio_oficial;
    }

    public void setSitio_oficial(String sitio_oficial) {
        this.sitio_oficial = sitio_oficial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getGenero() {
        return genero;
    }

    public void setGenero(String[] genero) {
        this.genero = genero;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Integer getTemp_actual() {
        return temp_actual;
    }

    public void setTemp_actual(Integer temp_actual) {
        this.temp_actual = temp_actual;
    }

    public Integer getEpisod_actual() {
        return episod_actual;
    }

    public void setEpisod_actual(Integer episod_actual) {
        this.episod_actual = episod_actual;
    }
}
