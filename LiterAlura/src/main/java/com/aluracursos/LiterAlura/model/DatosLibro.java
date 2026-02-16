package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title") String titloDelLibro,
    @JsonAlias("authors") Autor autores,
    @JsonAlias("languages") String idiomas,
    @JsonAlias("download_count") Integer cantidadDescargas
    ) {
}