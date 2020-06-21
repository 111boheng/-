package com.hengheng.dao;

import com.hengheng.domain.Orders;

import java.util.List;

public interface IOrderDao {
    public List<Orders> findAll();
}
