package com.crm.controller;

import com.crm.entity.CstActivity;
import com.crm.service.CstActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 客户交往记录分控制器
 */
@Controller
public class CstActivityController {
    @Autowired
    private CstActivityService cstActivityService;

    /**
     * 根据客户编号查询客户交往记录
     * @param m
     * @param no 客户编号
     * @return
     */
    @RequestMapping("/findCstActivityAll")
    public String findCstActivityAll(Model m, String no){
        List<CstActivity> cstActivityList = cstActivityService.findCstActivityAll(no);
        m.addAttribute("cstActivityList",cstActivityList);
        return "Customer/ActivitysPage";
    }

    /**
     * 根据id查询客户交往记录（跳转到编辑客户来往记录页面）
     * @param id 活动id
     * @param m
     * @return
     */
    @RequestMapping("/findCstActivityById")
    public String findCstActivityById(Integer id, Model m){
        CstActivity cstActivity = cstActivityService.findCstActivityById(id);
        m.addAttribute("cstActivity",cstActivity);
        return "Customer/ActivitysEdit";
    }

    /**
     * 根据id查询客户交往记录（跳转新建客户来往记录）
     * @param atvId 活动id
     * @param m
     * @return
     */
    @RequestMapping("/findCstActivityByAdd")
    public String findCstActivityByAdd(Integer atvId, Model m){
        CstActivity cstActivity = cstActivityService.findCstActivityById(atvId);
        m.addAttribute("cstActivity",cstActivity);
        return "Customer/ActivitysAdd";
    }

    /**
     * 新建客户交往记录
     * @param cstActivity 客户交往记录对象
     * @param request
     * @param response
     * @throws ParseException
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addActivity")
    public void addActivity(CstActivity cstActivity, HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String atvDate = request.getParameter("Date");
        cstActivity.setAtvDate(sdf.parse(atvDate));
        //调用添加方法
        int row = cstActivityService.addActivity(cstActivity);
        request.getRequestDispatcher("/findCstActivityAll?no="+cstActivity.getAtvCustNo()).forward(request,response);
    }

    /**
     * 修改客户交往记录
     * @return
     */
    @RequestMapping("/updateActivity")
    public void updateActivity(CstActivity cstActivity, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        //处理日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String atvDate = request.getParameter("Date");
        cstActivity.setAtvDate(sdf.parse(atvDate));
        //调用修改方法
        int row = cstActivityService.updateActivity(cstActivity);
        request.getRequestDispatcher("/findCstActivityAll?no="+cstActivity.getAtvCustNo()).forward(request,response);
    }

    /**
     * 根据id删除客户交往记录
     * @param id 活动表id
     * @param atvCustNo 客户编号
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delActivity")
    public void delActivity(Integer id,String atvCustNo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int row = cstActivityService.delActivity(id);
        request.getRequestDispatcher("/findCstActivityAll?no="+atvCustNo).forward(request,response);
    }
}