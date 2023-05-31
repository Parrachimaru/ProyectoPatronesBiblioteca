package com.edu.unipiloto.libro;

public class DAOLibroEx {

    public static void main(String[] args) {
        ILibroDAO Libro = new LibroDAOImplementation();
        Libro.getAllLibros();
        System.out.println("\n Se agrega un nuevo libro");
        Libro.addLibro(new Libro(2016, "pruebasss", "libardinni", "sapeee", true));
        Libro.getAllLibros();
        System.out.println("\n Se ELIMINA un libro");
        Libro.deleteLibro(new Libro(2016, "pruebasss", "libardinni", "sapeee", true));
        Libro.getAllLibros();

        System.out.println("\n buscar un libro");
        //LibroDAOImplementation dao = new LibroDAOImplementation();

    }

}
