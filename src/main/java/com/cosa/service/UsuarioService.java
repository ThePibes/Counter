package com.cosa.service;

import com.cosa.dao.UsuarioDao;
import com.cosa.pojo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alessandro
 */
@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDao usuarioDao;

    public void saveUsuario(Usuario usuario){
        String clave = usuario.getPassword();
        usuario.setPassword(passwordEncoder.encode(clave));
        usuarioDao.saveUsuario(usuario);
    }

    public void updateUsuario(Usuario usuario){
        usuarioDao.updateUsuario(usuario);
    }

    public List<Usuario> getByName(String nombre){

        return usuarioDao.getByName(nombre);
    }

    public Usuario findById(int id){
        return usuarioDao.findById(id);
    }

    public Usuario findByPatente(String nombre){
        return usuarioDao.findByNombre(nombre);
    }


}
