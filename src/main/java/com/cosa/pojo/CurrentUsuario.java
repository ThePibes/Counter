package com.cosa.pojo;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author Alessandro
 */
public class CurrentUsuario extends org.springframework.security.core.userdetails.User {
    private Usuario user;

    public CurrentUsuario(Usuario user) {

        super(user.getNombre(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public Usuario getUser() {
        return user;
    }

    public Role getRole() {
        return user.getRole();
    }
}
