package com.cosa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Implementación de DAOGenerics para tener acceso a CRUD
 * extender los demas daos para tener acceso de esta clase.
 *
 */
//desmarcar los warning.
@SuppressWarnings("unchecked")
@Repository
@Transactional
/** prototype: Scopes a single bean definition to any number of object instances.
 **  singleton: Scopes a single bean definition to a single object instance per Spring IoC container.
 **/
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K>{

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    private Class<? extends E> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor with parameters.
     */
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt;
        pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

/*    @Override
    public E find(Object key) {
        return null;
    }*/

    @Override
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @Override
    public List<E> getAll() {
        return currentSession().createCriteria(daoType).list();
    }
}
