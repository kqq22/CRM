package com.crm.controller;
import com.crm.entity.SalChance;
import com.crm.entity.SalPlan;
import com.crm.entity.SysUser;
import com.crm.service.SalChanceService;
import com.crm.service.SalPlanService;
import com.crm.service.SysUserService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售机会管理分控制器
 */
@Controller
public class SalChanceController {
    @Autowired
    private SalChanceService salChanceService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SalPlanService salPlanService;

    /**
     * SalChance
     * 分页查询+模糊查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/findSalChanceAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<SalChance> findSalChanceAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String name, String linkman, String title){
        int pageNum1 = pageNum==null?1:pageNum;
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum1,3);
        //调用业务类查询方法
        List<SalChance> list;
        if(name==""&&linkman==""&&title==""){
            list = salChanceService.findSalChanceAll();
        }else {
            SalChance salChance = new SalChance("%"+name+"%","%"+linkman+"%","%"+title+"%");
            list = salChanceService.findSalChanceByExample(salChance);
        }
        //返回Json对象
        PageInfo<SalChance> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * SaleManager
     * 分页查询+模糊查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/findSaleManagerAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<SalChance> findSaleManagerAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String name, String linkman, String title,String status){
        int pageNum1 = pageNum==null?1:pageNum;
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum1,3);
        //调用业务类查询方法
        List<SalChance> list;
        if (name==null){
            name="";
        }
        if (linkman==null){
            linkman="";
        }
        if (title==null){
            title="";
        }
        if (status==null){
            status="";
        }
        Integer statu = 0;
        if (status.equals("开发中")){
            System.out.println(status);
            statu = 2;
        }else if (status.equals("开发成功")){
            System.out.println(status);
            statu = 3;
        }else if (status.equals("开发失败")){
            statu = 4;
        }else if (status.equals("全部")){
            statu = 1;
        }
        if(name==""&&linkman==""&&title==""&&statu==0){
            list = salChanceService.findSalManagerAll();
        }else if(statu==1){
            SalChance salChances = new SalChance("%"+name+"%","%"+linkman+"%","%"+title+"%");
            salChances.setChcStatus(statu);
            list = salChanceService.findSalManagerByExamples(salChances);
        }else {
            SalChance salChances = new SalChance("%"+name+"%","%"+linkman+"%","%"+title+"%");
            salChances.setChcStatus(statu);
            list = salChanceService.findSalManagerByExample(salChances);
        }
        //返回Json对象
        PageInfo<SalChance> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 添加销售机会管理记录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addSalChance")
    public String addSalChance(SalChance salChance,HttpServletRequest request, HttpServletResponse response,String createdate) throws IOException, ParseException {
        HttpSession session = request.getSession();
        //转换日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date cdate = sdf.parse(createdate);
        int id = Integer.parseInt(session.getAttribute("uid").toString());
        salChance.setChcCreateDate(cdate);
        salChance.setChcCreateId(id);
        //调用添加方法
        int row = salChanceService.addSalChance(salChance);
        if(row==1){
            return "/Sale/SaleChance";
        }else {
            return "/Sale/AddSal";
        }
    }

    /**
     * 根据id查询单个SaleChance，跳转到销售机会分配
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleChanceById")
    public String findSaleChanceById(Integer id, Model m){
        SalChance salChance = salChanceService.findSalChanceById(id);
        List<SysUser> sysUserList = sysUserService.findSysUser();
        m.addAttribute("sysUserList",sysUserList);
        m.addAttribute("salChance",salChance);
        return "/Sale/AllotSale";
    }

    /**
     * 根据id查询单个SaleChance，跳转到制定计划
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleManagerById")
    public String findSaleManagerById(Integer id,Model m){
        findSaleChanceById(id,m);
        List<SalPlan> salPlanList =  salPlanService.findSalPlanBySalChanceId(id);
        m.addAttribute("salPlanList",salPlanList);
        return "/Sale/SetPlay";
    }

    /**
     * 根据id查询单个SaleChance，跳转到执行计划
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleManagersyIds")
    public String findSaleManagersyIds(Integer id,Model m){
        findSaleChanceById(id,m);
        List<SalPlan> salPlanList =  salPlanService.findSalPlanBySalChanceId(id);
        m.addAttribute("salPlanList",salPlanList);
        return "/Sale/ExecPlay";
    }

    /**
     * EditSale
     * 根据id查询单个SaleChance，跳转到修改销售计划管理
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleChanceByIds")
    public String findSaleChanceByIds(Integer id,Model m){
        findSaleChanceById(id,m);
        return "/Sale/EditSale";
    }

    /**
     * LookPlay.jsp
     * 根据id查询单个SaleChance(开发成功/开发失败)
     * @param id
     * @param m
     * @return
     * @throws ParseException
     */
    @RequestMapping("/findSaleChanceStatusById")
    public String findSaleChanceStatusById(Integer id,Model m ){
        findSaleManagersyIds(id,m);
        return "/Sale/LookPlay";

    }
    /**
     * 修改销售机会管理指派人
     * @param request
     * @param salChance
     */
    @RequestMapping("/updateSaleChanceDueTo")
    public String updateSaleChanceDueTo(SalChance salChance,HttpServletRequest request) throws ParseException {
        String currentTime = request.getParameter("cTime");
        int chcId = Integer.parseInt(request.getParameter("chcId"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        String username = request.getParameter("userName");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        salChance.setChcId(chcId);
        salChance.setChcDueDate(sdf.parse(currentTime));
        salChance.setChcDueId(userid);
        salChance.setChcDueTo(username);
        salChance.setChcStatus(2);
        int row = salChanceService.updateSalChanceById(salChance);
        if (row==1){
            return "/Sale/SaleChance";
        }else {
            return "/Sale/AllotSale";
        }
    }

    /**
     * 修改销售机会开发状态
     * @param salChance
     * @return
     */
    @RequestMapping("/updateSaleChanceStatus")
    public String updateSaleChanceStatus(SalChance salChance){
        int row = salChanceService.updateSalChanceById(salChance);
        return "/Sale/SaleManager";
    }
    /**
     * 修改销售机会管理指派人
     * @param salChance
     * @param request
     */
    @RequestMapping("/updateSaleChance")
    public String updateSaleChance(SalChance salChance,HttpServletRequest request){
        int chcId = Integer.parseInt(request.getParameter("chcId"));
        salChance.setChcId(chcId);
        int row = salChanceService.updateSalChanceById(salChance);
        if (row==1){
            return "/Sale/SaleChance";
        }else {
            return "/Sale/EditSale";
        }
    }

    /**
     * 删除销售机会管理记录
     * @param id
     */
    @RequestMapping("/delSaleChance")
    public String delBaseDict(Integer id){
        int row = salChanceService.delSalChanceById(id);
        if(row==1){
            return "/Sale/SaleChance";
        }else {
            return "删除失败";
        }
    }
}
