package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title") String tituloDelLibro,
    @JsonAlias("authors") List<DatosAutor> autores,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Integer cantidadDescargas
    ) {

    @Override
    public String toString() {
        return """
                ---------------- Datos del Libro ----------------""" + """
                
                - Titulo del Libro:  """ + tituloDelLibro + """
                
                - Autor: """ + autores + """
                
                - Idioma: """ + idiomas + """
                
                - Descargas: """ + cantidadDescargas + """
                
                ---------------------------------------""";
    }
}