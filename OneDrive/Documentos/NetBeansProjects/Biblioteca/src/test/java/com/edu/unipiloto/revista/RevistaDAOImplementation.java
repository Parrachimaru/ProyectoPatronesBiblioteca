package com.edu.unipiloto.revista;

import com.edu.unipiloto.libro.LibroDAOImplementation;
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

public class RevistaDAOImplementation implements IRevistaDAO {

    private String driver;
    private String url;
    private String login;
    private String password;
    private String sentencia;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    public RevistaDAOImplementation() {

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
            System.out.println("Cargando los controladores de la BASE DE DATOS REVISTAS");
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
//----------------------------------------------------------------------------------------------------------------------------------------------------////
    @Override
    public List<Revista> getAllRevistas() {

        List<Revista> listaRevistas = new ArrayList();

        if (connection == null) {
            conectar();
        }
        sentencia = "SELECT * FROM APP.REVISTAS";
        //convertir sentencia SQL
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            if (rs != null) {

                rs.first();
                do {
                    int id = rs.getInt(1);
                    String titulo = rs.getString(2);
                    String editor = rs.getString(3);
                    String fecha = rs.getString(4);
                    Boolean disponiblidad = rs.getBoolean(5);

                    System.out.println("Id Revista: " + id + "---> Titulo: " + titulo + "---> Editor: " + editor + "---> Fecha: " + fecha + "---> Disponibilidad: " + disponiblidad);
                    listaRevistas.add(new Revista(id, titulo, editor, fecha, true));
                } while (rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRevistas;

    }
//----------------------------------------------------------------------------------------------------------------------------------------------------////
    @Override
    public void EncuadernaRevista(Revista rv) {
        if (connection == null) {
            conectar();
        }

        List<Revista> revistasEncuadernadas = new ArrayList<>();

        sentencia = "SELECT * FROM APP.REVISTAS";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);

            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String titulo = rs.getString(2);
                    String editor = rs.getString(3);
                    String fecha = rs.getString(4);
                    boolean disponibilidad = rs.getBoolean(5);

                    if (fecha.contains("2023")) {
                        System.out.println("La revista fue encuadernada y guardada: " + titulo);
                        revistasEncuadernadas.add(new Revista(id, titulo, editor, fecha, disponibilidad));
                    }
                }
            }

            // Mostrar lista de revistas encuadernadas
            System.out.println("Revistas encuadernadas:");
            for (Revista revista : revistasEncuadernadas) {
                System.out.println("Título: " + revista.getTitulo() + ", Editor: " + revista.getEditor() + ", Fecha: " + revista.getFecha());
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------////
    @Override
    public void deleteRevista(Revista rv) {
        if (connection == null) {
            conectar();
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la Revista que desea eliminar: ");
        int idrevista = scanner.nextInt();

        sentencia = "DELETE FROM APP.REVISTAS WHERE id_revista = " + idrevista;

        //sentencia = "DELETE FROM APP.LIBROS WHERE id_libro = " + lb.getId_libro();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------////
    @Override
    public void addRevista(Revista rv) {
        if (connection == null) {
            conectar();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID de la revista:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el título de la revista:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el Editor de la revista:");
        String editor = scanner.nextLine();

        System.out.println("Ingrese la fecha de publicación");
        String fecha = scanner.nextLine();

        System.out.println("Ingrese la disponibilidad del libro (true/false):");
        boolean disponibilidad = scanner.nextBoolean();

        Revista nuevaRevista = new Revista(id, titulo, editor, fecha, disponibilidad);

        sentencia = "INSERT INTO APP.REVISTAS VALUES(" + nuevaRevista.getId_revista() + ", '"
                + nuevaRevista.getTitulo() + "', '" + nuevaRevista.getEditor() + "', '"
                + nuevaRevista.getFecha() + "', " + nuevaRevista.getDisponibilidad() + ")";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
            System.out.println("La revista se ha agregado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Revista searchRevista(int id) {
        if (connection == null) {
            conectar();
        }

        Revista revistaEncontrada = null;
        String consulta = "SELECT * FROM APP.REVISTAS WHERE id_revista = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese el ID de la revista:");
            int idr = scanner.nextInt();

            pstmt.setInt(1, idr);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id_revista = rs.getInt("id_revista");
                String titulo = rs.getString("titulo");
                String editor = rs.getString("editor");
                String fecha_publicacion = rs.getString("fecha_publicacion");
                boolean disponibilidad = rs.getBoolean("disponibilidad");

                revistaEncontrada = new Revista(id_revista, titulo, editor, fecha_publicacion, disponibilidad);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return revistaEncontrada;
    }

}
