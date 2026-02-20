
---

# 📚 LiterAlura - Catálogo de Libros con API y Base de Datos

## 🎯 Objetivo

Desarrollar una aplicación de consola en **Java con Spring Boot** que permita consultar libros desde la API pública de **Gutendex**, almacenarlos en una base de datos y realizar búsquedas avanzadas sobre libros y autores registrados.

El proyecto busca aplicar buenas prácticas, manejo de relaciones JPA, persistencia de datos y consumo de APIs externas.

---

## 🚀 Características

* 🔎 **Búsqueda de libros por título** mediante consumo de API externa.
* 💾 **Persistencia en base de datos** utilizando Spring Data JPA.
* 👤 Registro automático de autores evitando duplicados.
* 📖 Consulta de libros registrados en la base de datos.
* 🧑‍💼 Consulta de autores registrados con sus libros asociados.
* 📅 Búsqueda de autores vivos en un año determinado.
* 🌎 Filtro de libros por idioma (EN, ES, FR, PT).
* 🔝 Visualización de los 10 libros más descargados desde la API.
* 🔎 Búsqueda de autores por nombre.
* 🛡 Manejo de errores y validaciones de entrada.

---

## 🧠 Conocimientos necesarios para desarrollar este proyecto

Para el desarrollo de este proyecto se implmentaron conocimientos en:

### 📌 Java

* Programación orientada a objetos (POO)
* Streams
* Manejo de excepciones

### 📌 Spring Data JPA

* Repositorios (`JpaRepository`)
* Relaciones `@ManyToMany`
* Consultas personalizadas
* Persistencia y manejo de entidades

### 📌 Consumo de APIs REST

* Peticiones HTTP
* Manejo de JSON

---

## ⚙️ Manejo de errores y excepciones

El proyecto implementa manejo de errores para mejorar la robustez y experiencia de usuario:

### ✔ Validación de entrada de datos

* Captura de `InputMismatchException` cuando el usuario ingresa datos inválidos.
* Limpieza del buffer del `Scanner` para evitar errores encadenados.

### ✔ Prevención de duplicados

Antes de guardar un libro o autor:

* Se verifica si ya existe en la base de datos usando métodos como:

    * `findByTituloDelLibroIgnoreCase`
    * `findByNombreAutorIgnoreCase`

Esto evita inconsistencias y mantiene la integridad de los datos.

### ✔ Manejo de errores en persistencia

* Uso de bloques `try-catch` al guardar entidades.
* Captura genérica de excepciones en caso de fallos en la base de datos.

---

## 🗄 Modelo de Datos

El proyecto maneja una relación:

* 📚 **Libro**
* 👤 **Autor**
* Relación `@ManyToMany`

Un libro puede tener varios autores y un autor puede tener varios libros.

---

## 🛠 Requisitos

### 🔹 Software necesario

* Java 17 o superior
* Maven
* Spring Boot
* Base de datos (PostgreSQL)
* IDE (IntelliJ IDEA o VS Code)

### 🔹 Dependencias principales

* Spring Boot Starter Data JPA
* Spring Boot Starter Web
* Jackson (para JSON)
* Driver de base de datos

---

## 👨‍💻 Autor

**Jose Alejandro Madrigal Ruiz** 📧 [alejandromadrigal_1999@outlook.com]  
🔗 [LinkedIn: Jose Alejandro Madrigal Ruiz](www.linkedin.com/in/jose-alejandro-madrigal-ruiz-686126218)  
🐈 [GitHub: @AlejandroMadrigalDev](https://github.com/AlejandroMadrigalDev)

📌 Proyecto desarrollado para consolidar conocimientos en:

* Java
* Spring Boot
* JPA
* Consumo de APIs
* Modelado relacional

---