package com.hengheng.service.impl;

import com.hengheng.dao.IProductDao;
import com.hengheng.domain.Product;
import com.hengheng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() {

        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
