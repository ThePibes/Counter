package com.cosa.dao;

import com.cosa.pojo.Stock;

import java.util.List;

/**
 * Created by root on 21/01/17.
 */
public interface stockDAO {
    public boolean saveStock(Stock stock);
    public List<Stock> findAllStock();
}
