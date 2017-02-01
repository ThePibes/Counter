package com.cosa.service;

import com.cosa.dao.PeopleDao;
import com.cosa.pojo.People;
import com.cosa.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kp0cH4 on 28/01/17.
 */
@Service
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    public void save(People people)
    {
        peopleDao.add(people);
    }
    public List<People> listAll(){ return peopleDao.getAll(); }
    public void remove(int id)
    {
        People people = peopleDao.find(id);
        peopleDao.remove(people);
    }
    public People findById(int id){ return peopleDao.find(id);}
    public void update(People people){ peopleDao.update(people); }
}
