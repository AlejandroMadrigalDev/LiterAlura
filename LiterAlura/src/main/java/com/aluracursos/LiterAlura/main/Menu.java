package com.aluracursos.LiterAlura.main;

import java.util.Scanner;

public class Menu {
    Scanner teclado = new Scanner(System.in);

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libros
                    
                    0 - Salir
                    """;

            switch (opcion) {
                case 1:
                    //buscarLibros();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
