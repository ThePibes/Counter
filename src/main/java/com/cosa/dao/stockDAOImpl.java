package com.cosa.dao;

import com.cosa.pojo.Stock;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by root on 21/01/17.
 */
@Transactional
@Repository
public class stockDAOImpl implements stockDAO{
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean saveStock(Stock stock){
        try{
            getSession().save(stock);
            return true;
        }catch (Exception e){
            String msj = e.getMessage();
            return false;
        }
    }

    @Override
    public List<Stock> findAllStock() {
        Query query = getSession().createQuery("from Stock");
        return query.list();
    }
}
