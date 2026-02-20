package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String tituloDelLibro;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();
    private String idiomas;
    private Integer cantidadDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.tituloDelLibro = datosLibro.tituloDelLibro();
        this.autores = new ArrayList<>();
        this.idiomas = String.join(", ", datosLibro.idiomas());
        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloDelLibro() {
        return tituloDelLibro;
    }

    public void setTituloDelLibro(String tituloDelLibro) {
        this.tituloDelLibro = tituloDelLibro;
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return """
                ---------------- Datos del Libro ----------------""" + """
                
                - Titulo del Libro:  """ + tituloDelLibro + """
                
                - Autor: """ + autores + """
                
                - Idioma: """ + idiomas + """
                
                - Descargas: """ + cantidadDescargas + """
                
                -------------------------------------------------""";
    }

    public String informacionDetallada() {
        String nombresAutores = autores.stream()
                .map(Autor::getNombreAutor)
                .collect(Collectors.joining(", "));

        return String.format("""
            ------- LIBRO ENCONTRADO -------
            Título: %s
            Autores: %s
            Idioma: %s
            Descargas: %d
            --------------------------------""",
                tituloDelLibro, nombresAutores, idiomas, cantidadDescargas);
    }
}
