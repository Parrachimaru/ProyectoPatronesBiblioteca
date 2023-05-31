
package com.edu.unipiloto.revista;


public class DAORevistaEx {
    
    public static void main(String[] args) {
        IRevistaDAO Revista = new RevistaDAOImplementation();
        Revista.getAllRevistas();
    }
    
}
