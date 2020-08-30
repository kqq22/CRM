package com.crm.controller;

import com.crm.entity.CstCustomer;
import com.crm.service.CstCustomerService;
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
 * 客户信息管理分控制器
 */
@Controller
public class CstCustomerController {
    @Autowired
    private CstCustomerService cstCustomerService;

    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/getCstCustomerAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<CstCustomer> getCstCustomerAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, CstCustomer cstCustomer) {
        //必须放在list前面
        PageHelper.startPage(pageNum, 3);
        //调用业务类查询方法
        List<CstCustomer> list;
        if(cstCustomer.getCustRegion().equals("全部")){
            cstCustomer.setCustRegion("");
        }
        if (cstCustomer.getCustName().equals("") && cstCustomer.getCustNo().equals("") && cstCustomer.getCustRegion().equals("") && cstCustomer.getCustManagerName().equals("")&&cstCustomer.getCustLevel()==0) {
            list = cstCustomerService.findCstCustomerAll();
        } else {
            list = cstCustomerService.findCstCustomerByExample(cstCustomer);
        }
        PageInfo<CstCustomer> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 查询单个CstCustomer
     * @param no
     * @param m
     * @return
     */
    @RequestMapping("findCstCustomerById")
    public String findCstCustomerById(String no, Model m){
        CstCustomer cstCustomer = cstCustomerService.findCstCustomerByNo(no);
        m.addAttribute("cstCustomer",cstCustomer);
        return "Customer/CustomerEdit";
    }

    /**
     * 修改客户信息
     * @param cstCustomer
     * @return
     */
    @RequestMapping("/updateCstCustomer")
    public String updateCstCustomer(CstCustomer cstCustomer, HttpServletRequest request){
        int row = cstCustomerService.updateCstCustomerByNo(cstCustomer);
        if (row==1){
            System.out.println(row);
            return "/Customer/CustomerPage";
        }else{
            System.out.println(row);
            return "";
        }
    }

    /**
     * 删除客户信息
     * @param no
     * @return
     */
    @RequestMapping("/delCstCustomer")
    public String delCstCustomer(String no){
        int row = cstCustomerService.delCstCustomerByNo(no);
        System.out.println(row);
        return "/Customer/CustomerPage";
    }
}
