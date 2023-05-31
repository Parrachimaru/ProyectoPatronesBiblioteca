package com.edu.unipiloto.usuarios;

import java.util.List;

public interface Usuarios {

    void acceder();

    public List<UsuarioProxy> getAllUser();

    public void updateLibro(UsuarioProxy us);

    public void deleteUser(UsuarioProxy us);

    public void addUser(UsuarioProxy us);

    public UsuarioProxy searchUser(int id);

}
