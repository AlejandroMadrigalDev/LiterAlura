package com.aluracursos.LiterAlura.main;

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

    public void muestraMenuPrincipal(){
        System.out.println("Llmando al menu menu menu menu!!!!!!!!!!!!");
    }
}
