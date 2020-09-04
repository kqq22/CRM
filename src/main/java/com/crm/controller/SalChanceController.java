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
import java.util.Date;
import java.util.List;

/**
 * 销售机会管理分控制器
 */
@Controller
public class SalChanceController {
    //注入销售机会管理业务逻辑接口
    @Autowired
    private SalChanceService salChanceService;

    //注入用户业务逻辑接口
    @Autowired
    private SysUserService sysUserService;

    //注入客户开发计划业务逻辑接口
    @Autowired
    private SalPlanService salPlanService;

    /**
     * SalChance 销售机会管理
     * 分页查询+模糊查询
     * @param pageNum 页码
     * @return
     */
    @RequestMapping(value="/findSalChanceAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<SalChance> findSalChanceAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String name, String linkman, String title){
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum,3);
        //调用业务类查询方法
        List<SalChance> list;
        if(name==""&&linkman==""&&title==""){
            //条件为空，调用查询所有销售机会管理信息的方法
            list = salChanceService.findSalChanceAll();
        }else {
            //根据条件进行模糊查询
            SalChance salChance = new SalChance("%"+name+"%","%"+linkman+"%","%"+title+"%");
            list = salChanceService.findSalChanceByExample(salChance);
        }
        //返回Json对象
        PageInfo<SalChance> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * SaleManager 客户开发计划
     * 分页查询+模糊查询
     * @param pageNum 页码
     * @return
     */
    @RequestMapping(value="/findSaleManagerAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<SalChance> findSaleManagerAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String name, String linkman, String title,String status){
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum,3);
        //调用业务类查询方法
        List<SalChance> list;
        //判断是否为空，如果为空，重新复制为字符串的空""
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
        //判断状态
        if (status.equals("开发中")){
            statu = 2;
        }else if (status.equals("开发成功")){
            statu = 3;
        }else if (status.equals("开发失败")){
            statu = 4;
        }else if (status.equals("全部")){
            statu = 1;
        }

        if(name==""&&linkman==""&&title==""&&statu==0){
            //为空，调用查询所有客户开发计划信息的方法
            list = salChanceService.findSalManagerAll();
        }else if(statu==1){
            //根据条件进行模糊查询
            SalChance salChances = new SalChance("%"+name+"%","%"+linkman+"%","%"+title+"%");
            salChances.setChcStatus(statu);
            list = salChanceService.findSalManagerByExamples(salChances);
        }else {
            //根据条件进行模糊查询
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
     * @throws IOException
     */
    @RequestMapping("/addSalChance")
    public String addSalChance(SalChance salChance, HttpServletRequest request ){
        HttpSession session = request.getSession();
        int id = Integer.parseInt(session.getAttribute("uid").toString());//当前用户id
        salChance.setChcCreateDate(new Date());
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
     * 根据id查询单个SaleChance（跳转到销售机会分配页面）
     * @param id 销售机会管理id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleChanceById")
    public String findSaleChanceById(Integer id, Model m){
        //查询单个SaleChance
        SalChance salChance = salChanceService.findSalChanceById(id);
        //查询所有用户
        List<SysUser> sysUserList = sysUserService.findSysUser();
        m.addAttribute("sysUserList",sysUserList);
        m.addAttribute("salChance",salChance);
        return "/Sale/AllotSale";
    }

    /**
     * 根据id查询单个SaleChance（跳转到制定计划页面）
     * @param id 销售机会管理id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleManagerById")
    public String findSaleManagerById(Integer id, Model m){
        //根据id查询单个SaleChance
        findSaleChanceById(id,m);
        //根据id查询客户开发计划信息
        List<SalPlan> salPlanList =  salPlanService.findSalPlanBySalChanceId(id);
        m.addAttribute("salPlanList",salPlanList);
        return "/Sale/SetPlay";
    }

    /**
     * 根据id查询单个SaleChance（跳转到执行计划页面）
     * @param id 销售计划管理id
     * @param m
     * @return
     */
    @RequestMapping("/findSaleManagersyIds")
    public String findSaleManagersyIds(Integer id, Model m){
        //根据id查询单个SaleChance
        findSaleChanceById(id,m);
        //根据id查询客户开发计划信息
        List<SalPlan> salPlanList =  salPlanService.findSalPlanBySalChanceId(id);
        m.addAttribute("salPlanList",salPlanList);
        return "/Sale/ExecPlay";
    }

    /**
     * EditSale
     * 根据id查询单个SaleChance（跳转到修改销售计划管理页面）
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
    public String updateSaleChanceDueTo(SalChance salChance,HttpServletRequest request){
        //获取参数
        int userId = Integer.parseInt(request.getParameter("userId"));
        int chcId = Integer.parseInt(request.getParameter("chcId"));
        //获取选中的分配者
        SysUser user = sysUserService.findSysUserById(userId);
        //设置参数
        salChance.setChcId(chcId);
        salChance.setChcDueDate(new Date());
        salChance.setChcDueId(userId);
        salChance.setChcDueTo(user.getUserName());
        salChance.setChcStatus(2);
        //调用修改方法
        int row = salChanceService.updateSalChanceById(salChance);
        if (row==1){
           return "/Sale/SaleChance";
        }else {
           return "/Sale/AllotSale";
        }
    }

    /**
     * 修改销售机会开发状态
     * @param salChance 销售机会管理对象
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
    public String updateSaleChance(SalChance salChance, HttpServletRequest request){
        //获取参数
        int chcId = Integer.parseInt(request.getParameter("chcId"));//销售机会管理id
        salChance.setChcId(chcId);
        //调用修改方法
        int row = salChanceService.updateSalChanceById(salChance);
        if (row==1){
            return "/Sale/SaleChance";
        }else {
            return "/Sale/EditSale";
        }
    }

    /**
     * 删除销售机会管理记录
     * @param id 销售机会管理id
     */
    @RequestMapping("/delSaleChance")
    public String delBaseDict(Integer id){
        int row = salChanceService.delSalChanceById(id);
        return "/Sale/SaleChance";
    }
}
