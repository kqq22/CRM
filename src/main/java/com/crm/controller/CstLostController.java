package com.crm.controller;

import com.crm.entity.CstLost;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 客户流失分控制器
 */
@Controller
public class CstLostController {
    @Autowired
    private CstLostService cstLostService;

    /**
     *
     * 分页查询+模糊查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/findCstLostAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<CstLost> findCstLostAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, CstLost cstLost){
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum,3);
        //调用业务类查询方法
        List<CstLost> list;
        if(cstLost.getLstCustName()==""&&cstLost.getLstCustManagerName()==""&&cstLost.getLstStatus()==""){
            list = cstLostService.findCstLostAll();
        }else {
            list = cstLostService.findCstLostByExample(cstLost);
        }
        //返回Json对象
        PageInfo<CstLost> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 根据id查询客户流失信息（跳转到追加暂缓流失措施的页面）
     * @param id
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
     * @param id
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
     * @param id
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
     * @param cstLost
     * @param lstId
     * @param request
     */
    @RequestMapping("/updateCstLost")
    public String updateCstLost(CstLost cstLost, Integer lstId, HttpServletRequest request){
        String reLstDelay = request.getParameter("reLstDelay");
        cstLost.setLstDelay(cstLost.getLstDelay()+","+reLstDelay);
        int row = cstLostService.updateCstLostById(cstLost);
        return "Customer/LostsPage";
    }

    /**
     * 修改客户流失信息（添加流失原因）
     * @param cstLost
     * @param lstId
     */
    @RequestMapping("/updateCstLosts")
    public String updateCstLosts(CstLost cstLost, Integer lstId){
        cstLost.setLstStatus("3");
        int row = cstLostService.updateCstLostById(cstLost);
        return "Customer/LostsPage";
    }



}
