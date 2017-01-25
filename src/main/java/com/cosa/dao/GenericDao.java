package com.cosa.dao;

import java.util.List;

/**
 * Created by root on 25/01/17.
 * @param <E>
 * @param <K>
 *
 */
public interface GenericDao<E,K> {
    public void add(E entity) ;
    public void saveOrUpdate(E entity) ;
    public void update(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll();
}
