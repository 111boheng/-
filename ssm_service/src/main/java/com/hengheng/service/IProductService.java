package com.hengheng.service;

import com.hengheng.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();

    void save(Product product);
}
