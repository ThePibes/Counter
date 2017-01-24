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

    public boolean save(Stock stock){
        return stockdao.saveStock(stock);
    }

    public List<Stock> findAllStock(){
        return stockdao.findAllStock();
    }
}
