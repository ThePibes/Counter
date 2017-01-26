package com.cosa.service;

import com.cosa.pojo.CurrentUsuario;
import com.cosa.pojo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Alessandro
 */
@Service
public class CurrentUsuarioDetalleService implements UserDetailsService {

    @Autowired
    private  UsuarioService usuarioService;

    @Autowired
    public CurrentUsuarioDetalleService(UsuarioService userService) {
        this.usuarioService = userService;
    }

    @Override
    public CurrentUsuario loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = usuarioService.findByPatente(username);
        return new CurrentUsuario(user);

    }


}