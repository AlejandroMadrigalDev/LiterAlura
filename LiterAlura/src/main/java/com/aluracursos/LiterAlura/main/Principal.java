package com.aluracursos.LiterAlura.main;

import com.aluracursos.LiterAlura.model.*;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.ConsumoAPI;
import com.aluracursos.LiterAlura.service.ConvierteDatos;
import org.aspectj.apache.bcel.Repository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private List<Autor> autores;
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
                case 2:
                    mostrarLibrosRegistradosEnBBDD();
                    break;
                case 3:
                    mostrarAutoresRegistradosEnBBDD();
                    break;
                case 4:
                    buscarAutoresPorFecha();
                    break;
                case 5:
                    buscarLibrosPorIdioma();
                    break;
                case 6:
                    mostrar10LibrosMasDescargados();
                    break;
                case 7:
                    buscarAutoresPorNombre();
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
                System.out.println("Libro encontrado y almacenado: " + datos);
            } catch (Exception e) {
                System.out.println("Error al guardar: " + e.getMessage());
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    public void mostrarLibrosRegistradosEnBBDD() {
        libros = repositorio.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados aún.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    public void mostrarAutoresRegistradosEnBBDD() {
        var autores = autorRepository.buscarAutoresConLibros();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados aún.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    public void buscarAutoresPorFecha() {
        System.out.println("Ingresa el año vivo del autor(es) que desea buscar: ");
        try {
            var fechaIngresada = teclado.nextInt();
            teclado.nextLine();

            List<Autor> autoresVivos = autorRepository.buscarAutoresPorFecha(fechaIngresada);

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + fechaIngresada);
            } else {
                System.out.println("--- Autores vivos en el año " + fechaIngresada + " ---");
                autoresVivos.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingresa un número de año válido.");
            teclado.nextLine();
        }
    }

    public void buscarLibrosPorIdioma() {
        System.out.println("Elija un idioma para buscar libros");
        System.out.println("1. [en] - Inglés");
        System.out.println("2. [es] - Español");
        System.out.println("3. [fr] - Frances");
        System.out.println("4. [pt] - Portugués");
        try {
            var opcion = teclado.nextLine();
            String idioma = switch (opcion) {
                case "1" -> "en";
                case "2" -> "es";
                case "3" -> "fr";
                case "4" -> "pt";
                default -> opcion;
            };

            List<Libro> librosPorIdioma = repositorio.buscarLibrosPorIdioma(idioma);

            if (librosPorIdioma.isEmpty()) {
                System.out.println("No se encontraron libros por este idioma " + idioma);
            } else {
                System.out.println("--- Libros registrados que se encuentran en el idioma " + idioma + " ---");
                librosPorIdioma.forEach(l -> System.out.println(l.informacionDetallada()));
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingresa un idioma de la lista.");
            teclado.nextLine();
        }
    }

    public void mostrar10LibrosMasDescargados() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datosBiblioteca = conversor.obtenerDatos(json, DatosBiblioteca.class);

        System.out.println("--- TOP 10 LIBROS MÁS DESCARGADOS ---");

        datosBiblioteca.resultados().stream()
                .sorted(Comparator.comparing(DatosLibro::cantidadDescargas).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    public void buscarAutoresPorNombre() {
        System.out.println("Ingresa el nombre del autor para buscar");
        var nombreIngresado = teclado.nextLine();

        List<Autor> autoresExistentes = autorRepository.buscarAutoresEnBBDD(nombreIngresado);
        if (autoresExistentes.isEmpty()) {
            System.out.println("No se encontró ningún autor con el nombre: " + nombreIngresado);
        } else {
            System.out.println("--- Autores encontrados ---");
            autoresExistentes.forEach(System.out::println);
        }
    }
}