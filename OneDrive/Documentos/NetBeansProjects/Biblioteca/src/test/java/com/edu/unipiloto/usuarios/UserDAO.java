package com.edu.unipiloto.usuarios;

import com.edu.unipiloto.libro.Libro;
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

public class UserDAO implements Usuarios {

    private String driver;
    private String url;
    private String login;
    private String password;
    private String sentencia;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    public UserDAO() {
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

    @Override
    public void acceder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioProxy> getAllUser() {
        List<UsuarioProxy> listaUsuarios = new ArrayList();

        if (connection == null) {
            conectar();
        }
        sentencia = "SELECT * FROM APP.USUARIO";
        //convertir sentencia SQL
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sentencia);
            if (rs != null) {

                rs.first();
                do {
                    int cedula = rs.getInt(1);
                    String nombres = rs.getString(2);
                    String apellidos = rs.getString(3);
                    int id_prestamo = rs.getInt(4);
                    String fecha_prestamo = rs.getString(5);
                    String fecha_devolucion = rs.getString(6);
                    boolean miembro = rs.getBoolean(7);

                    System.out.println("cedula: " + cedula + "---> nombres: " + nombres + "---> apellidos: " + apellidos + "---> id_prestamo: " + id_prestamo + "---> fecha_prestamo: " + fecha_prestamo
                            + "---> fecha_devolucion: " + fecha_devolucion + "---> Staff: " + miembro);
                    listaUsuarios.add(new UsuarioProxy(cedula, nombres, apellidos, id_prestamo, fecha_prestamo, fecha_devolucion, miembro));
                } while (rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }

    @Override
    public void updateLibro(UsuarioProxy us) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUser(UsuarioProxy us) {
        if (connection == null) {
            conectar();
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese cedula del usuario a eliminar: ");
        int cedula = scanner.nextInt();

        sentencia = "DELETE FROM APP.USUARIO WHERE cedula = " + cedula;

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUser(UsuarioProxy us) {
        if (connection == null) {
            conectar();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la cedula del usuario:");
        int cedula = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Ingrese nombres:");
        String nombres = scanner.nextLine();

        System.out.println("Ingrese apellidos:");
        String apellidos = scanner.nextLine();

        System.out.println("Ingrese el id del prestamo:");
        int id_prestamo = scanner.nextInt();

        System.out.println("Ingrese la fecha en la que desea prestamo:");
        String fecha_prestamo = scanner.nextLine();

        System.out.println("Ingrese la fecha limite de devolución:");
        String fecha_devolucion = scanner.nextLine();

        System.out.println("Ingrese el tipo de membresia (true/false):");
        boolean miembro = scanner.nextBoolean();

        UsuarioProxy nuevoUser = new UsuarioProxy(cedula, nombres, apellidos, id_prestamo, fecha_prestamo, fecha_devolucion, miembro);

        sentencia = "INSERT INTO APP.USUARIO VALUES(" + nuevoUser.getCedula() + ", '"
                + nuevoUser.getNombre() + "', '" + nuevoUser.getApellidos() + "', "
                + nuevoUser.getId_prestamo() + ", '" + nuevoUser.getFecha_prestamo() + "', '" + nuevoUser.getFecha_devolucion() + "', " + nuevoUser.isMiembro() + ")";

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
            System.out.println("El usuario se ha agregado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public UsuarioProxy searchUser(int id) {
        if (connection == null) {
            conectar();
        }

        UsuarioProxy UsuarioEncontrado = null;
        String consulta = "SELECT * FROM APP.USUARIO WHERE cedula = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese la cedula del usuario:");
            int uss = scanner.nextInt();

            pstmt.setInt(1, uss);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int cedula = rs.getInt("cedula");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String id_prestamo = rs.getString("id_prestamo");
                String fecha_prestamo = rs.getString("fecha_prestamo");
                String fecha_devolucion = rs.getString("fecha_devolucion");
                boolean miembro = rs.getBoolean("miembro");

                UsuarioEncontrado = new UsuarioProxy(cedula, nombres, apellidos, id, fecha_prestamo, fecha_devolucion, miembro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UsuarioEncontrado;
    }

}
