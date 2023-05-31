package com.edu.unipiloto.libro;

import java.util.Scanner;

public class DAOLibroEx {

    private static LibroDAOImplementation libroDAO = new LibroDAOImplementation();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*ILibroDAO Libro = new LibroDAOImplementation();
        Libro.getAllLibros();
        System.out.println("\n Se agrega un nuevo libro");
        Libro.addLibro(new Libro(2016, "pruebasss", "libardinni", "sapeee", true));
        Libro.getAllLibros();
        //System.out.println("\n Se ELIMINA un libro");
        //Libro.deleteLibro(new Libro(2016, "pruebasss", "libardinni", "sapeee", true));
       // Libro.getAllLibros();

        System.out.println("\n buscar libro");
        
        ILibroDAO libroDAO = new LibroDAOImplementation();
        
         Libro libroEncontrado = libroDAO.searchLibro(105);
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ID: " + libroEncontrado.getId_libro());
            System.out.println("Título: " + libroEncontrado.getTitulo());
            System.out.println("Autor: " + libroEncontrado.getAutor());
            System.out.println("Editorial: " + libroEncontrado.getEditorial());
            System.out.println("Disponibilidad: " + libroEncontrado.getDisponibilidad());
        } else {
            System.out.println("No se encontró ningún libro con ese ID.");
        }*/

        mostrarMenu();
    }

    public static void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("----- MENÚ -----");
            System.out.println("1. Buscar un libro");
            System.out.println("2. Ver la lista de libros");
            System.out.println("3. Agregar un libro");
            System.out.println("4. Borrar un libro");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción deseada: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    verListaLibros();
                    break;
                case 3:
                    agregarLibro();
                    break;
                case 4:
                    borrarLibro();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }

        // Cerrar el Scanner antes de salir del programa
        scanner.close();
    }

    public static void buscarLibro() {

        ILibroDAO libroDAO = new LibroDAOImplementation();

        Libro libroEncontrado = libroDAO.searchLibro(105);
        if (libroEncontrado != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ID: " + libroEncontrado.getId_libro());
            System.out.println("Título: " + libroEncontrado.getTitulo());
            System.out.println("Autor: " + libroEncontrado.getAutor());
            System.out.println("Editorial: " + libroEncontrado.getEditorial());
            System.out.println("Disponibilidad: " + libroEncontrado.getDisponibilidad());
            mostrarMenu();
        } else {
            System.out.println("No se encontró ningún libro con ese ID.");

        }
        mostrarMenu();
    }

    public static void verListaLibros() {

        ILibroDAO Libro = new LibroDAOImplementation();
        Libro.getAllLibros();
        mostrarMenu();
    }

    public static void agregarLibro() {
        ILibroDAO Libro = new LibroDAOImplementation();

        System.out.println("\n agregar un nuevo libro");
        Libro.addLibro(new Libro(2016, "pruebasss", "libardinni", "sapeee", true));
        Libro.getAllLibros();
        mostrarMenu();

    }

    public static void borrarLibro() {
        ILibroDAO Libro = new LibroDAOImplementation();

        System.out.println("\n Se ELIMINA un libro");
        Libro.deleteLibro(new Libro(2016, "pruebasss", "libardinni", "sapeee", true));
        mostrarMenu();

    }

}
