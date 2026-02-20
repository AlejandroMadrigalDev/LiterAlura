package com.aluracursos.LiterAlura.main;

import com.aluracursos.LiterAlura.model.*;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.ConsumoAPI;
import com.aluracursos.LiterAlura.service.ConvierteDatos;
import org.aspectj.apache.bcel.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository repositorio;
    private final AutorRepository autorRepository;
    private List<Libro> libros;
    Menu menuPrincipal = new Menu();

    public Principal(LibroRepository repositorio, AutorRepository autorRepository) {
        this.repositorio = repositorio;
        this.autorRepository = autorRepository;
    }

    public void muestraMenuPrincipal() {
        var opcion = -1;
        while (opcion != 0) {
            menuPrincipal.mostrarMenu();
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private void buscarLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar: ");
        var nombreLibroBuscado = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibroBuscado.replace(" ", "+"));
        var datosConsultaLibro = conversor.obtenerDatos(json, DatosBiblioteca.class);

        Optional<DatosLibro> libroBuscado = datosConsultaLibro.resultados().stream()
                .filter(l -> l.tituloDelLibro().toUpperCase().contains(nombreLibroBuscado.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibro datos = libroBuscado.get();
            Optional<Libro> libroExistente = repositorio.findByTituloDelLibroIgnoreCase(datos.tituloDelLibro());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya se encuentra registrado en la base de datos. No se puede volver a registrar.");
                return;
            }
            Libro libroEncontrado = new Libro(datos);

            // Procesar autores profesionalmente
            List<Autor> autoresFinales = datos.autores().stream()
                    .map(datosAutor -> autorRepository.findByNombreAutorIgnoreCase(datosAutor.nombreAutor())
                                .orElseGet(() -> autorRepository.save(new Autor(datosAutor))))
                    .collect(Collectors.toList());

            libroEncontrado.setAutores(autoresFinales);

            try {
                repositorio.save(libroEncontrado);
                System.out.println("Libro encontrado y almacenado en la base de datos: " + datos);
            } catch (Exception e) {
                System.out.println("Error al guardar: " + e.getMessage());
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}