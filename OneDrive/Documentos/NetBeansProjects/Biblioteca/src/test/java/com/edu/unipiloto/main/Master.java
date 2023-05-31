package com.edu.unipiloto.main;

import java.util.Scanner;

public class Master {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Limpiar la consola
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Mostrar el encabezado del menú
            System.out.println("==================================");
            System.out.println("|       MENÚ PRINCIPAL           |");
            System.out.println("==================================");
            System.out.println("| Opciones:                      |");
            System.out.println("|      1. Registrar Usuario      |");
            System.out.println("|      2. Prestamo               |");
            System.out.println("|      3. Libros                 |");
            System.out.println("|      4. Revistas               |");
            System.out.println("|      0. Salir                  |");
            System.out.println("==================================");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el entero

            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    com.edu.unipiloto.usuarios.UsuarioEX.main(args);
                    break;

                case 2:
                    com.edu.unipiloto.prestamo.PrestamoEX.main(args);
                    break;
                case 3:
                    com.edu.unipiloto.libro.DAOLibroEx.main(args);
                    break;
                case 4:
                    com.edu.unipiloto.revista.DAORevistaEx.main(args);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

            // Esperar antes de volver a mostrar el menú
            System.out.print("Presione Enter para continuar...");
            scanner.nextLine();

        } while (opcion != 0);

        scanner.close();
    }

}
