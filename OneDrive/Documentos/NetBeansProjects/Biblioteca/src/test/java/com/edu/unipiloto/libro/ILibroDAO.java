
package com.edu.unipiloto.libro;

import java.util.List;

public interface ILibroDAO {
    
    public List<Libro> getAllLibros();

    public void updateLibro(Libro lb);

    public void deleteLibro(Libro lb);

    public void addLibro(Libro lb);

    public Libro searchLibro(int id);
    
}
