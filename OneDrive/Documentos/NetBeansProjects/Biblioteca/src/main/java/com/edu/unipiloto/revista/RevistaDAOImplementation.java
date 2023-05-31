package com.edu.unipiloto.revista;

import com.edu.unipiloto.libro.Libro;
import com.edu.unipiloto.libro.LibroDAOImplementation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                    listaRevistas.add(new Revista(id, titulo, editor, fecha, id));
                } while (rs.next());

            }

        } catch (SQLException ex) {
            Logger.getLogger(LibroDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRevistas;

    }

    @Override
    public void updateLibro(Revista rv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteLibro(Revista rv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addLibro(Revista rv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Revista searchLibro(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
