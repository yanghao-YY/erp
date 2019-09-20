package com.saltyfish.erp.service;

import com.saltyfish.erp.domain.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import com.saltyfish.erp.dao.shoeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class shoeServiceImpl implements shoeService {
    @Autowired
    shoeMapper shoeMapper;
    @Override
    public void addStock(String plu) {
        shoeMapper.addStock(plu);

    }

    @Override
    public void cutStock(String plu) {
        shoeMapper.cutStock(plu);
    }

    @Override
    public List<Shoes> query(String model, String plu, String color, String size, String remark) {
        List<Shoes> shoes = shoeMapper.query(model, plu, color, size, remark);
        return shoes;
    }

    @Override
    public void add(String model, String plu, String color, String size, String stock, String remark) {
        shoeMapper.addShoes(model,plu,color,size,stock,remark);
    }

    @Override
    public void update(int id, String model, String plu, String color, String size, String stock, String remark) {
      shoeMapper.updateShoes(id, model, plu, color, size, stock, remark);
    }

    @Override
    public void delete(int id) {
        shoeMapper.deleteShoes(id);
    }
}
