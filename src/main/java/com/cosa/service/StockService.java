package com.cosa.service;

import com.cosa.dao.stockDAO;
import com.cosa.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 21/01/17.
 */
@Service
public class StockService {

    @Autowired
    private stockDAO stockdao;

    public void save(Stock stock){
        stockdao.add(stock);
    }
    public List<Stock> listAll(){ return stockdao.getAll(); }
    public void remove(int id)
    {
        Stock stock = stockdao.find(id);
        stockdao.remove(stock);
    }
    public Stock findById(int id){ return stockdao.find(id);}
    public void update(Stock stock){ stockdao.update(stock); }
}
