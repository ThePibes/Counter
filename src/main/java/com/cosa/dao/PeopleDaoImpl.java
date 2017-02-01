package com.cosa.dao;

import com.cosa.pojo.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kp0cH4 on 28/01/17.
 */
@Repository
@Transactional
public class PeopleDaoImpl extends GenericDaoImpl<People, Integer> implements PeopleDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
