package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosBiblioteca(
        @JsonAlias("results") List<DatosLibro> resultados
) {
}
