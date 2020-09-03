package com.crm.controller;

import com.crm.entity.SalPlan;
import com.crm.service.SalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 销售计划分控制器
 */
@Controller
public class SalPlanController {
    @Autowired
    private SalPlanService salPlanService;

    /**
     * 根据销售机会id查询销售计划
     * @param id 销售机会管理id
     * @param m
     * @return
     */
    @RequestMapping("findSalPlanBySalChanceId")
    public String findSalPlanBySalChanceId(Integer id, Model m){
        List<SalPlan> salPlanList = salPlanService.findSalPlanBySalChanceId(id);
        m.addAttribute("salPlanList",salPlanList);
        return "";
    }

    /**
     * 添加销售计划
     * @param salPlan
     * @return
     */
    @RequestMapping("addSalPlan")
    public void addSalPlan(SalPlan salPlan, HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String planDate = request.getParameter("planDate");
        Date plaDate = sdf.parse(planDate);
        int chcid = Integer.parseInt(request.getParameter("plaChcId"));
        salPlan.setPlaChcId(chcid);
        salPlan.setPlaDate(plaDate);
        int row = salPlanService.addSalPlan(salPlan);
        if (row==1){
            response.sendRedirect("/findSaleManagerById?id="+chcid);
        }else{
            response.sendRedirect("/index.jsp");
        }
    }

    /**
     * 根据id修改销售计划
     * @param salPlan
     * @param chcid
     * @return
     */
    @RequestMapping("/updateSalPlan")
    public String updateSalPlan(SalPlan salPlan,Integer chcid){
        int row = salPlanService.updateSalPlan(salPlan);
        return "forward:/findSaleManagerById?id="+chcid;
    }

    /**
     * 根据id修改销售计划的执行结果
     * @param salPlan
     * @param chcid
     * @return
     */
    @RequestMapping("/updateSalPlans")
    public String updateSalPlans(SalPlan salPlan,Integer chcid){
        int row = salPlanService.updateSalPlan(salPlan);
        return "forward:/findSaleManagersyIds?id="+chcid;
    }

    /**
     * 根据id删除销售计划
     * @param plaid
     * @param chcid
     * @return
     */
    @RequestMapping("/delSalPlan")
    public String delSalPlan(int plaid,int chcid){
        int row = salPlanService.delSalPlan(plaid);
        if(row==1){
            return "forward:/findSaleManagerById?id="+chcid;
        }else {
            System.out.println(row);
            return "forward:/findSaleManagerById?id="+chcid;
        }

    }
}
