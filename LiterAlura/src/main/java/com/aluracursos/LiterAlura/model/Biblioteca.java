package com.aluracursos.LiterAlura.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class Biblioteca {
    private Integer cantidadDeLibros;
    private String siguientePagina;
    private String PaginaAnterior;
    private List<DatosLibro> resultados;
}
