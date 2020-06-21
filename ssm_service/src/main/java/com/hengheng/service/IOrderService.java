package com.hengheng.service;

import com.hengheng.domain.Orders;

import java.util.List;

public interface IOrderService {
    public List<Orders> findAll(int page,int size);
}
