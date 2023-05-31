package com.edu.unipiloto.usuarios;

public class UsuarioEX {

    public static void main(String[] args) {
        UsuarioProxy staffProxy = new UsuarioProxy("Staff");
        staffProxy.acceder();  // Acceso concedido como Staff

        UsuarioProxy socioProxy = new UsuarioProxy("Socio");
        socioProxy.acceder();  // Acceso concedido como Socio
    }

}
