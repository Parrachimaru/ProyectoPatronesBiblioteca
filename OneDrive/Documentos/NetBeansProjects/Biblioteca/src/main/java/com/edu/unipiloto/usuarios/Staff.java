package com.edu.unipiloto.usuarios;

public class Staff implements Usuarios {

    @Override
    public void acceder() {
        System.out.println("Acceso concedido como Staff");
    }

}
