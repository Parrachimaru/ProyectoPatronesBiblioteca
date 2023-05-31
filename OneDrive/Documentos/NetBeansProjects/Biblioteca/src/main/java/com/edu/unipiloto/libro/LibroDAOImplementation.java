package com.edu.unipiloto.libro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibroDAOImplementation implements ILibroDAO {

    private String driver;
    private String url;
    private String login;
    private String password;
    private String sentencia;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    //constructor
    public LibroDAOImplementation() {
        driver = "org.apache.derby.jdbc.ClientDriver";
        url = "jdbc:derby://localhost:1527/sample";
        login = "app";
        password = "app";
        sentencia = "";
        connection = null;
        statement = null;
        rs = null;
    }

    //metodo conectar
    protected void conectar() {
        try {
            Class.forName(driver);
            System.out.println("Cargar los controladores de la base de datos funcionando full");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodo desconectar
    public void desconectar(Connection con) {

        try {
            con.close();
        } catch (SQLException ex) {
            //Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //metodos abtractos ILIBRODAO
    @Override
    public List<Libro> getAllLibros() {

        List<Libro> listaLibros = new ArrayList();

        if (connection == null) {
            conectar();
        }
        sentencia = "SELECT * FROM APP.LIBROS";
        //convertir sentencia SQL
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            if (rs != null) {

                rs.first();
                do {
                    int id = rs.getInt(1);
                    String titulo = rs.getString(2);
                    String autor = rs.getString(3);
                    String editorial = rs.getString(4);
                    Boolean disponiblidad = rs.getBoolean(5);

                    System.out.println("Id Libro: " + id + "---> Titulo: " + titulo + "---> Autor: " + autor + "---> Editorial: " + editorial + "---> Disponibilidad: " + disponiblidad);
                    listaLibros.add(new Libro(id, titulo, autor, editorial, true));
                } while (rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLibros;
    }

    @Override
    public void updateLibro(Libro lb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteLibro(Libro lb) {

        if (connection == null) {
            conectar();
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del libro a eliminar: ");
        int idLibro = scanner.nextInt();

        sentencia = "DELETE FROM APP.LIBROS WHERE id_libro = " + idLibro;

        //sentencia = "DELETE FROM APP.LIBROS WHERE id_libro = " + lb.getId_libro();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void addLibro(Libro lb) {
        if (connection == null) {
            conectar();
        }
        /////prueba////
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del libro:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el autor del libro:");
        String autor = scanner.nextLine();

        System.out.println("Ingrese la editorial del libro:");
        String editorial = scanner.nextLine();

        System.out.println("Ingrese la disponibilidad del libro (true/false):");
        boolean disponibilidad = scanner.nextBoolean();

        // Crear el objeto Libro con los datos ingresados por el usuario
        Libro nuevoLibro = new Libro(id, titulo, autor, editorial, disponibilidad);

        // Insertar el nuevo libro en la base de datos
        sentencia = "INSERT INTO APP.LIBROS VALUES(" + nuevoLibro.getId_libro() + ", '"
                + nuevoLibro.getTitulo() + "', '" + nuevoLibro.getAutor() + "', '"
                + nuevoLibro.getEditorial() + "', " + nuevoLibro.getDisponibilidad() + ")";
        /////termina la prueba////

        //sentencia = "INSERT INTO APP.LIBROS VALUES(" + lb.getId_libro() + ", '" + lb.getTitulo() + "', '" + lb.getAutor() + "', '" + lb.getEditorial() + "', " + lb.getDisponibilidad() + ")";
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
            System.out.println("El libro se ha agregado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Libro searchLibro(int id) {
        if (connection == null) {
            conectar();
        }

        Libro libroEncontrado = null;
        String consulta = "SELECT * FROM APP.LIBROS WHERE id_libro = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese el ID del libro:");
            int idl = scanner.nextInt();

            pstmt.setInt(1, idl);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id_libro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                boolean disponibilidad = rs.getBoolean("disponibilidad");

                libroEncontrado = new Libro(id_libro, titulo, autor, editorial, disponibilidad);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return libroEncontrado;
    }

}
