package com.gestionusuarios.dashboard.dao;

// * cada clase es una tabla


import com.gestionusuarios.dashboard.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
