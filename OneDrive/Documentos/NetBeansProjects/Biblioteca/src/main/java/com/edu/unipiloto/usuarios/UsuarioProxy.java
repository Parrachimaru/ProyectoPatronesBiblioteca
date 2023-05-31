package com.edu.unipiloto.usuarios;

public class UsuarioProxy implements Usuarios {

    private Usuarios usuarioReal;
    private String tipoUsuario;

    public UsuarioProxy(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public void acceder() {
        if (usuarioReal == null) {
            usuarioReal = crearUsuarioReal();
        }
        usuarioReal.acceder();
    }

    private Usuarios crearUsuarioReal() {
        if (tipoUsuario.equalsIgnoreCase("Staff")) {
            return new Staff();
        } else if (tipoUsuario.equalsIgnoreCase("Socio")) {
            return new Socio();
        } else {
            throw new IllegalArgumentException("Tipo de usuario inválido");
        }
    }

}
