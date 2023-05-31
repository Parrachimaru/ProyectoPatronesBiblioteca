
package com.edu.unipiloto.revista;

import java.util.List;

public interface IRevistaDAO {
    
     public List<Revista> getAllRevistas();

    public void EncuadernaRevista(Revista rv);

    public void deleteRevista(Revista rv);

    public void addRevista(Revista rv);

    public Revista searchRevista(int id);
    
}
