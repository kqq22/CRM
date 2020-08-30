package com.crm.controller;

import com.crm.entity.CstCustomer;
import com.crm.entity.Orders;
import com.crm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 订单分控制器
 */
@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/findOrdersAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<Orders> getCstCustomerAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String custName) {
        //必须放在list前面
        PageHelper.startPage(pageNum, 3);
        //调用业务类查询方法
        List<Orders> list = ordersService.findOrdersAll(custName);
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/findOrdersAlls")
    public String findOrdersAlls(String cName,String cNo, Model m){
        m.addAttribute("cName",cName);
        m.addAttribute("cNo",cNo);
        return "/Customer/OrderPage";
    }
}
