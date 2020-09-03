package com.crm.controller;

import com.crm.entity.CstCustomer;
import com.crm.entity.CstLost;
import com.crm.service.CstCustomerService;
import com.crm.service.CstLostService;
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
import java.util.Date;
import java.util.List;

/**
 * 客户流失分控制器
 */
@Controller
public class CstLostController {
    @Autowired
    private CstLostService cstLostService;

    @Autowired
    private CstCustomerService cstCustomerService;

    /**
     *
     * 分页查询+模糊查询
     * @param pageNum 页码
     * @return
     */
    @RequestMapping(value="/findCstLostAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<CstLost> findCstLostAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, CstLost cstLost){
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum,3);
        List<CstLost> list;
        if(cstLost.getLstCustName()==""&&cstLost.getLstCustManagerName()==""&&cstLost.getLstStatus()==""){//所有条件为空
            //调用查询所有客户流失信息的方法
            list = cstLostService.findCstLostAll();
        }else {
            //根据条件进行模糊查询
            list = cstLostService.findCstLostByExample(cstLost);
        }
        //返回Json对象
        PageInfo<CstLost> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 根据id查询客户流失信息（跳转到追加暂缓流失措施的页面）
     * @param id 客户流失信息id
     * @param m
     * @return
     */
    @RequestMapping("/findCstLostById")
    public String findCstLostById(Integer id, Model m){
        CstLost cstLost = cstLostService.findCstLostById(id);
        m.addAttribute("cstLost",cstLost);
        return "Customer/LostPause";
    }

    /**
     * 根据id查询客户流失信息（跳转到追加暂缓流失措施的页面）
     * @param id 客户流失信息id
     * @param m
     * @return
     */
    @RequestMapping("/findCstLostByIds")
    public String findCstLostByIds(Integer id, Model m){
        CstLost cstLost = cstLostService.findCstLostById(id);
        m.addAttribute("cstLost",cstLost);
        return "Customer/LostEnter";
    }

    /**
     * 根据id查询客户流失信息（跳转到确认流失页面）
     * @param id 客户流失信息id
     * @param m
     * @return
     */
    @RequestMapping("/findCstLostByIdLook")
    public String findCstLostByIdLook(Integer id, Model m){
        CstLost cstLost = cstLostService.findCstLostById(id);
        m.addAttribute("cstLost",cstLost);
        return "Customer/LostLook";
    }

    /**
     * 修改客户流失信息（追加暂缓流失措施）
     * @param cstLost 客户流失信息对象
     * @param request
     */
    @RequestMapping("/updateCstLost")
    public String updateCstLost(CstLost cstLost, HttpServletRequest request){
        //获取参数
        String reLstDelay = request.getParameter("reLstDelay");//流失措施
        cstLost.setLstDelay(cstLost.getLstDelay()+","+reLstDelay);//在原有的流失措施上追加
        int row = cstLostService.updateCstLostById(cstLost);
        return "Customer/LostsPage";
    }

    /**
     * 修改客户流失信息（添加流失原因）
     * @param cstLost 客户流失信息对象
     */
    @RequestMapping("/updateCstLosts")
    public String updateCstLosts(CstLost cstLost){
        cstLost.setLstStatus("3");//状态改为已流失，3为已流失
        cstLost.setLstLostDate(new Date());
        //修改客户信息状态为流失
        CstCustomer cstCustomer = new CstCustomer();
        cstCustomer.setCustNo(cstLost.getLstCustNo());
        cstCustomer.setCustStatus("2");
        int cstRow = cstCustomerService.updateCstCustomer(cstCustomer);
        int row = cstLostService.updateCstLostById(cstLost);
        return "Customer/LostsPage";
    }

    /**
     * 根据状态查询客户信息（客户流失分析）
     * @param cstLost 客户流失信息对象
     * @param m
     * @return
     */
    @RequestMapping("/findCstLostByStatus")
    public String findCstLostByStatus(CstLost cstLost, Model m){
        List<CstLost> cstLostList;
        //判断条件是否为null，把null值变为字符串的空""
        if(cstLost.getLstCustName()==null){
            cstLost.setLstCustName("");
        }
        if(cstLost.getLstCustManagerName()==null){
            cstLost.setLstCustManagerName("");
        }
        if(cstLost.getLstCustName()==""&&cstLost.getLstCustManagerName()==""){
            //条件为空，调用查询所有客户流失信息的方法
            cstLostList = cstLostService.findCstLostByStatus("3");//3 为已流失的客户
        }else {
            //根据条件进行模糊查询
            cstLost.setLstStatus("3");
            cstLostList = cstLostService.findCstLostByExample(cstLost);
        }
        m.addAttribute("cstLostList",cstLostList);
        return "Report/LostReport";
    }
}
