package com.hengheng.controller;

import com.hengheng.domain.Orders;
import com.hengheng.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required =true,defaultValue = "3")int page,@RequestParam(name = "size",required = true,defaultValue = "3")int size){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");

        return mv;
    }
}
