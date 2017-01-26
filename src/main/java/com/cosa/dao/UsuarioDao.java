package com.cosa.dao;

import com.cosa.pojo.Usuario;

import java.util.List;

/**
 * @author Alessandro
 */
public interface UsuarioDao {

    public void saveUsuario(Usuario usuario);

    public void updateUsuario(Usuario usuario);

    public List<Usuario> getByName(String nombre);

    public Usuario findByNombre(String Nombre);

    public Usuario findById(int id);


}
