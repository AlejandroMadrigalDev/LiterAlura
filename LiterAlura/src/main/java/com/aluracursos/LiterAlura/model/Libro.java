package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titloDelLibro;
    private String idiomas;
    private Integer cantidadDescargas;
    @ManyToOne
    private Autor autores;

    public Libro(Long id, String titloDelLibro, Autor autores, String idiomas, Integer cantidadDescargas) {
        this.id = id;
        this.titloDelLibro = titloDelLibro;
        this.autores = autores;
        this.idiomas = idiomas;
        this.cantidadDescargas = cantidadDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitloDelLibro() {
        return titloDelLibro;
    }

    public void setTitloDelLibro(String titloDelLibro) {
        this.titloDelLibro = titloDelLibro;
    }

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {
        this.autores = autores;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titloDelLibro='" + titloDelLibro + '\'' +
                ", autores=" + autores +
                ", idiomas='" + idiomas + '\'' +
                ", cantidadDescargas=" + cantidadDescargas +
                '}';
    }
}
