package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Autor;
import com.aluracursos.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloDelLibroIgnoreCase(String tituloDelLibro);

    @Query("SELECT l FROM Libro l LEFT JOIN FETCH l.autores WHERE l.idiomas = :idioma")
    List<Libro> buscarLibrosPorIdioma(@Param("idioma")String idioma);
}
