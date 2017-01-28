package com.cosa.dao;

import com.cosa.pojo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alessandro
 */
@Transactional
@Repository
public class UsuarioDaoImpl  implements  UsuarioDao{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        getSession().save(usuario);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        getSession().update(usuario);
    }

    @Override
    public List<Usuario> getByName(String nombre) {
        Criteria criteria = getSession().createCriteria(Usuario.class)
                .add(Restrictions.like("nombre", "%"+nombre+"%"));
        return criteria.list();
    }

    @Override
    public Usuario findByNombre(String nombre) {
        Criteria criteria = getSession().createCriteria(Usuario.class).add(Restrictions.eq("nombre",nombre));
        return (Usuario) criteria.uniqueResult();
    }

    @Override
    public Usuario findById(int id){
        Criteria criteria = getSession().createCriteria(Usuario.class).add(Restrictions.eq("id",id));
        return (Usuario) criteria.uniqueResult();
    }
}
