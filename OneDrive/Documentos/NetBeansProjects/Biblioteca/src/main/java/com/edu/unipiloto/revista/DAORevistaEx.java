package com.edu.unipiloto.revista;

import java.util.Scanner;

public class DAORevistaEx {

    private static RevistaDAOImplementation libroDAO = new RevistaDAOImplementation();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("----- MENÚ -----");
            System.out.println("1. Buscar revista");
            System.out.println("2. Ver la lista de revistas");
            System.out.println("3. Agregar una revista");
            System.out.println("4. Borrar una revistas");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarRevista();
                    break;
                case 2:
                    verListaRevistas();
                    break;
                case 3:
                    agregarRevista();
                    break;
                case 4:
                    borrarRevista();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }

        scanner.close();
    }

    public static void buscarRevista() {

        IRevistaDAO rvsDAO = new RevistaDAOImplementation();

        Revista RevistaEncontrada = rvsDAO.searchRevista(105);
        if (RevistaEncontrada != null) {
            System.out.println("Revista Encontrada:");
            System.out.println("ID: " + RevistaEncontrada.getId_revista());
            System.out.println("Título: " + RevistaEncontrada.getTitulo());
            System.out.println("Editor: " + RevistaEncontrada.getEditor());
            System.out.println("Fecha publicación: " + RevistaEncontrada.getFecha());
            System.out.println("Disponibilidad: " + RevistaEncontrada.getDisponibilidad());
            mostrarMenu();
        } else {
            System.out.println("No se encontró ningúna revista con ese ID.");
        }
        mostrarMenu();
    }

    public static void verListaRevistas() {

        IRevistaDAO Revista = new RevistaDAOImplementation();
        Revista.getAllRevistas();
        mostrarMenu();
    }

    public static void agregarRevista() {
        IRevistaDAO Revista = new RevistaDAOImplementation();
        System.out.println("\n agregar nueva revista");
        Revista.addRevista(new Revista(5, "xxxx", "xxxx", "xxxx", true));
        Revista.getAllRevistas();
        Revista.EncuadernaRevista(new Revista(0, "", "", "", true));
        mostrarMenu();

    }

    public static void borrarRevista() {
        IRevistaDAO Revista = new RevistaDAOImplementation();
        System.out.println("\n Se ELIMINA un libro");
        Revista.deleteRevista(new Revista(5, "xxxx", "xxxx", "xxxx", true));
        mostrarMenu();

    }
}
