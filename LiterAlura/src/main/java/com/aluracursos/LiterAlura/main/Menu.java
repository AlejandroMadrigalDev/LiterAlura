package com.aluracursos.LiterAlura.main;

import java.util.Scanner;

public class Menu {
    public void mostrarMenu() {
        var menuPrincipal = """
                #################################################
                #################################################
                           Elija una opcion del Menu
                
                1 - Buscar libro por titulo.
                2 - Mostrar libros registrados en la Biblioteca.
                3 - Mostrar autores registrados en la biblioteca.
                4 - Mostrar autores vivos en un determinado año.
                5 - Mostrar libros por idioma.
                
                0 - Salir de la aplicacion
                #################################################
                #################################################
                """;

        System.out.println(menuPrincipal);
    }
}