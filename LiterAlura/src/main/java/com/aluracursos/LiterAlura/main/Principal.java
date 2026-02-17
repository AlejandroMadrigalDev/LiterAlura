package com.aluracursos.LiterAlura.main;

import com.aluracursos.LiterAlura.model.DatosLibro;
import com.aluracursos.LiterAlura.model.Libro;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.ConsumoAPI;
import com.aluracursos.LiterAlura.service.ConvierteDatos;
import org.aspectj.apache.bcel.Repository;

import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private List<Libro> libros;
    Menu menuPrincipal = new Menu();

    public void muestraMenuPrincipal() {
        var opcion = -1;
        while (opcion != 0) {
            menuPrincipal.mostrarMenu();
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    mostrarLibros();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    private void mostrarLibros() {
        var json = consumoApi.obtenerDatos(URL_BASE + "84/");
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        System.out.println(datos);
    }
}