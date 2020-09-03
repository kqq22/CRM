package com.crm.controller;

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
import javax.servlet.http.HttpServletRequest;
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
     * @param pageNum 页码
     * @return
     */
    @RequestMapping(value="/findOrdersAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<Orders> getCstCustomerAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String custName) {
        //必须放在list前面 分页帮助类 插件
        PageHelper.startPage(pageNum, 3);
        //调用业务类查询方法
        List<Orders> list = ordersService.findOrdersAll(custName);
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 跳转到分页查询方法
     * 将客户编号和客户名称存入model中，用于页面显示
     * @param cName 客户编号
     * @param cNo 客户名称
     * @param m
     * @return
     */
    @RequestMapping("/findOrdersAlls")
    public String findOrdersAlls(String cName, String cNo, Model m){
        m.addAttribute("cName",cName);
        m.addAttribute("cNo",cNo);
        return "/Customer/OrderPage";
    }


    /**
     * 客户贡献分析
     * @param request
     * @param m
     * @return
     */
    @RequestMapping("/findContributeReport")
    public String findContributeReport(HttpServletRequest request, Model m){
        //获取参数
        String odrCustomer = request.getParameter("odrCustomer");
        String odrDate = request.getParameter("odrDate");
        //判断条件是否为null，如果为null，重新赋值为字符串的空""
        if(odrCustomer==null){
            odrCustomer ="";
        }
        if(odrDate==null){
            odrDate ="";
        }
        List<Orders> ordersList = ordersService.findContributeReport(odrCustomer,odrDate);
        m.addAttribute("ordersList",ordersList);
        return "/Report/ContributeReport";

    }
}
