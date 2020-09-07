package com.crm.controller;

import com.crm.entity.CstCustomer;
import com.crm.entity.CstLinkman;
import com.crm.service.CstCustomerService;
import com.crm.service.CstLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 联系人分控制器类
 */
@Controller
public class CstLinkmanController {
    @Autowired
    private CstLinkmanService cstLinkmanService;

    @Autowired
    private CstCustomerService cstCustomerService;
    /**
     * 根据客户信息管理编号查询联系人
     * @param m
     * @return
     */
    @RequestMapping("/findCstLinkmanByNo")
    public String findCstLinkmanByNo(Model m,String no,String name){
        //调用查询方法
        List<CstLinkman> linkmanList = cstLinkmanService.findCstLinkmanByNo(no);
        m.addAttribute("linkmanList",linkmanList);
        m.addAttribute("no",no);
        m.addAttribute("name",name);
        return "Customer/LinkManPage";
    }

    /**
     * 根据id查询联系人信息（跳转到编辑联系人页面）
     * @param id 联系人id
     * @param m
     * @return
     */
    @RequestMapping("/findCstLinkmanByid")
    public String findCstLinkmanByid(Integer id, Model m){
        CstLinkman cstLinkman = cstLinkmanService.findCstLinkmanById(id);
        m.addAttribute("cstLinkman",cstLinkman);
        return "Customer/LinkManEdit";
    }

    /**
     * 根据id查询联系人（跳转到新建联系人页面）
     * @param no 联系人no
     * @param m
     * @return
     */
    @RequestMapping("/findCstLinkmanBylkmId")
    public String findCstLinkmanAdd(String no, Model m){
        CstCustomer cstCustomer = cstCustomerService.findCstCustomerByNo(no);
        m.addAttribute("cstCustomer",cstCustomer);
        return "Customer/LinkManAdd";
    }

    /**
     * 添加联系人
     * @param cstLinkman 联系人对象
     * @return
     */
    @RequestMapping("/addCstLinkman")
    public void addCstLinkman(CstLinkman cstLinkman, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int row = cstLinkmanService.addCstLinkman(cstLinkman);
        request.getRequestDispatcher("/findCstLinkmanByNo?no="+cstLinkman.getLkmCustNo()+"&name="+cstLinkman.getLkmCustName()).forward(request,response);
    }

    /**
     * 修改联系人信息
     * @param cstLinkman 联系人对象
     * @return
     */
    @RequestMapping("/updateCstLinkman")
    public void updateCstLinkman(CstLinkman cstLinkman, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int row = cstLinkmanService.updateLinkman(cstLinkman);
        String no = cstLinkman.getLkmCustNo();//编号
        //存放，用于页面显示
        request.setAttribute("no",no);
        request.getRequestDispatcher("/findCstLinkmanByNo?no="+no+"&name="+cstLinkman.getLkmCustName()).forward(request,response);
    }

    /**
     * 删除联系人
     * @param lkmId 联系人id
     * @param lkmcustNo 客户编号
     * @param request
     * @param response
     */
    @RequestMapping("/delCstLinkman")
    public void delCstLinkman(Integer lkmId, String lkmcustNo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int row = cstLinkmanService.delLinkman(lkmId);
        request.getRequestDispatcher("/findCstLinkmanByNo?no="+lkmcustNo).forward(request,response);
    }
}

