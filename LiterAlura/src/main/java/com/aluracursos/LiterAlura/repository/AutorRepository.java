package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByNombreAutorIgnoreCase(String nombreAutor);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> buscarAutoresConLibros();

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros WHERE a.fechaNacimiento <= :fechaIngresada AND (a.fechaMuerte IS NULL OR a.fechaMuerte >= :fechaIngresada)")
    List<Autor> buscarAutoresPorFecha(@Param("fechaIngresada")Integer fechaIngresada);
}
