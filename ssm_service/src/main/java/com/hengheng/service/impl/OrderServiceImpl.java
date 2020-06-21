package com.hengheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.hengheng.dao.IOrderDao;
import com.hengheng.domain.Orders;
import com.hengheng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }
}
