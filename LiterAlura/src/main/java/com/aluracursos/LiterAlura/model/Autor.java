package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nombreAutor;
    private Integer fechaNacimiento;
    private Integer fechaMuerte;
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombreAutor = datosAutor.nombreAutor();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaMuerte = datosAutor.fechaMuerte();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    @Override
    public String toString() {
        String nombresLibros = (libros != null)
                ? libros.stream()
                .map(Libro::getTituloDelLibro)
                .collect(Collectors.joining(", "))
                : "Sin libros registrados";

        return String.format("""
            ---------------- Datos del Autor ----------------
            - Nombre del Autor:  %s
            - Año de Nacimiento: %s
            - Año de Muerte:     %s
            - Libros escritos:   [%s]
            -------------------------------------------------""",
                nombreAutor,
                (fechaNacimiento != null ? fechaNacimiento : "Sin registro"),
                (fechaMuerte != null ? fechaMuerte : "Sin registro"),
                nombresLibros);
    }
}
