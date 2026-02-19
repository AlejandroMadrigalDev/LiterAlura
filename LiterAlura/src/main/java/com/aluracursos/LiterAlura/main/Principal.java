package com.aluracursos.LiterAlura.main;

import com.aluracursos.LiterAlura.model.DatosBiblioteca;
import com.aluracursos.LiterAlura.model.DatosLibro;
import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.ConsumoAPI;
import com.aluracursos.LiterAlura.service.ConvierteDatos;
import org.aspectj.apache.bcel.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository repositorio;
    private List<Libro> libros;
    Menu menuPrincipal = new Menu();

    public Principal(LibroRepository repositorio) {
        this.repositorio = repositorio;
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
            Libro libroEncontrado = new Libro(datos);
            repositorio.save(libroEncontrado);
            System.out.println("Libro encontrado: " + datos);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}