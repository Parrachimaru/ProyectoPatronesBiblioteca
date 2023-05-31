package com.edu.unipiloto.usuarios;

import java.util.Scanner;

public class UsuarioEX {

    private static UserDAO usser = new UserDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("----- MENÚ -----");
            System.out.println("1. Buscar Usuario");
            System.out.println("2. Ver la lista de Usuarios");
            System.out.println("3. Agregar un Usuario");
            System.out.println("4. Borrar un Usuario");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarUsuario();
                    break;
                case 2:
                    verListaUsuarios();
                    break;
                case 3:
                    agregarNuevoUsuario();
                    break;
                case 4:
                    borrarUsuario();
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

    public static void buscarUsuario() {

        Usuarios user = new UserDAO();

        UsuarioProxy usuarioEncontrado = user.searchUser(105);
        if (usuarioEncontrado != null) {
            System.out.println("Usuario Encontrada:");
            System.out.println("Cedula: " + usuarioEncontrado.getCedula());
            System.out.println("Nombres: " + usuarioEncontrado.getNombre());
            System.out.println("Apellidos: " + usuarioEncontrado.getApellidos());
            System.out.println("ID prestamo: " + usuarioEncontrado.getId_prestamo());
            System.out.println("Fecha de prestamo: " + usuarioEncontrado.getFecha_prestamo());
            System.out.println("Fecha devolución: " + usuarioEncontrado.getFecha_devolucion());
            System.out.println("Parte del Staff: " + usuarioEncontrado.isMiembro());
            mostrarMenu();
        } else {
            System.out.println("No se encontró ningúnusuarico con esa cedula.");
        }
        mostrarMenu();
    }

    public static void verListaUsuarios() {

        Usuarios huser = new UserDAO();
        huser.getAllUser();
        mostrarMenu();
    }

    public static void agregarNuevoUsuario() {
        Usuarios userr = new UserDAO();
        System.out.println("\n agregar nuevo usuario");
        userr.addUser(new UsuarioProxy(5, "nombre", "apellidos", 5, "fecha_prestamo", "fecha_devolucion", true));
        userr.getAllUser();
        mostrarMenu();

    }

    public static void borrarUsuario() {
        Usuarios userrr = new UserDAO();
        System.out.println("\n Se ELIMINA un usuario");
        userrr.deleteUser(new UsuarioProxy(5, "nombre", "apellidos", 5, "fecha_prestamo", "fecha_devolucion", true));
        mostrarMenu();

    }

}
