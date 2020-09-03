package com.crm.controller;

import com.crm.entity.OrdersLine;
import com.crm.service.OrdersLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 订单详情分控制器
 */
@Controller
public class OrdersLineController {
    @Autowired
    private OrdersLineService ordersLineService;

    /**
     * 根据订单编号查询所有订单详情
     * @param orderId 订单编号
     * @param m
     * @return
     */
    @RequestMapping("/findOrdersLineAll")
    public String findOrdersLineAll(Integer orderId, Model m){
        List<OrdersLine> ordersLineList = ordersLineService.findOrdersLineAll(orderId);
        m.addAttribute("ordersLineList",ordersLineList);
        return "Customer/OrderDetail";
    }
}
