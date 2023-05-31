package com.edu.unipiloto.usuarios;

public class Socio implements Usuarios {

    @Override
    public void acceder() {
        System.out.println("Acceso concedido como Socio");
    }

}
