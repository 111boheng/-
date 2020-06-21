package com.hengheng.dao;

import com.hengheng.domain.Product;

import java.util.List;

public interface IProductDao {
    public List<Product> findAll();

    void save(Product product);
}
