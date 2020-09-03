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
     * 分页查询+模糊查询
     * @param pageNum 页码
     * @param cstCustomer 客户信息对象
     * @return
     */
    @RequestMapping(value="/getCstCustomerAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<CstCustomer> getCstCustomerAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, CstCustomer cstCustomer) {
        //必须放在list前面 分页帮助类 插件
        PageHelper.startPage(pageNum, 3);
        //调用业务类查询方法
        List<CstCustomer> list;
        //判断选项是否为全部，为全部就赋值为空，查询所有客户信息
        if(cstCustomer.getCustRegion().equals("全部")){
            cstCustomer.setCustRegion("");
        }
        if (cstCustomer.getCustName().equals("") && cstCustomer.getCustNo().equals("") && cstCustomer.getCustRegion().equals("") && cstCustomer.getCustManagerName().equals("")&&cstCustomer.getCustLevel()==0) {
            //查询所有客户信息
            list = cstCustomerService.findCstCustomerAll();
        } else {
            //根据条件进行模糊查询
            list = cstCustomerService.findCstCustomerByExample(cstCustomer);
        }
        PageInfo<CstCustomer> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 查询单个CstCustomer （跳转到客户信息编辑页面）
     * @param no 客户编号
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
     * @param cstCustomer 客户信息对象
     * @return
     */
    @RequestMapping("/updateCstCustomer")
    public String updateCstCustomer(CstCustomer cstCustomer){
        int row = cstCustomerService.updateCstCustomerByNo(cstCustomer);
        return "/Customer/CustomerPage";

    }

    /**
     * 删除客户信息
     * @param no 客户编号
     * @return
     */
    @RequestMapping("/delCstCustomer")
    public String delCstCustomer(String no){
        int row = cstCustomerService.delCstCustomerByNo(no);
        return "/Customer/CustomerPage";
    }

    /**
     * 客户构成分析
     * @return
     */
    @RequestMapping("/findCstCustomerMakeReport")
    public String findCstCustomerMakeReport(HttpServletRequest request, Model m){
        //获取参数
        String contribute = request.getParameter("contribute");//报表方式
        List<CstCustomer> cstCustomerList =  cstCustomerService.findCstCustomerMakeReport(contribute);
        m.addAttribute("cstCustomerList",cstCustomerList);
        return "/Report/MakeReport";
    }
}