
package com.edu.unipiloto.revista;

import java.util.List;

public interface IRevistaDAO {
    
     public List<Revista> getAllRevistas();

    public void updateLibro(Revista rv);

    public void deleteLibro(Revista rv);

    public void addLibro(Revista rv);

    public Revista searchLibro(int id);
    
}
