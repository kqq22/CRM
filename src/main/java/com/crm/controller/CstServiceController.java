package com.crm.controller;
import com.crm.entity.CstService;
import com.crm.entity.SysUser;
import com.crm.service.CstServiceService;
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
 * 服务管理分控制器
 */
@Controller
public class CstServiceController {
    @Autowired
    private CstServiceService cstServiceService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 服务创建
     * @param cstService
     * @param request
     * @return
     */
    @RequestMapping("/addCstService")
    public String addCstService(CstService cstService, HttpServletRequest request){
        HttpSession session = request.getSession();
        int uid = Integer.parseInt(session.getAttribute("uid").toString());
        String uname = session.getAttribute("uname").toString();
        cstService.setSvrCreateDate(new Date());
        cstService.setSvrCreateId(uid);
        cstService.setSvrCreateBy(uname);
        cstService.setSvrStatus("新创建");
        int row = cstServiceService.addCstService(cstService);
        return "CustomerService/ServiceCreate";
    }

    /**
     *
     * 分页查询+模糊查询（服务分配）（服务归档）
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/findCstServiceAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<CstService> findCstServiceAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, CstService cstService) {
        //必须放在list前面  分页帮助类 插件
        PageHelper.startPage(pageNum,1);
        //调用业务类查询方法
        List<CstService> list;
        if(cstService.getSvrStartDate()!=""&&cstService.getSvrEndDate()!=""){
            if(cstService.getSvrStatus().equals("全部")){
                cstService.setSvrStatus("");
            }
            if(cstService.getSvrType().equals("全部")){
                cstService.setSvrType("");
            }
            list = cstServiceService.findCstServiceByExample(cstService);
        }else{
            if(cstService.getSvrType()=="全部"&&cstService.getSvrStatus()=="全部"){
                list = cstServiceService.findCstServiceAll();
            }else {
                list = cstServiceService.findCstServiceByExample(cstService);
            }
        }
        //返回Json对象
        PageInfo<CstService> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 查询所有用户（跳转到服务分配）
     * @param m
     * @return
     */
    @RequestMapping("/findUser")
    public  String findUser(Model m){
        List<SysUser> userList = sysUserService.findSysUser();
        m.addAttribute("userList",userList);
        return "CustomerService/ServiceAllot" ;
    }

    /**
     * 查询所有用户（跳转到服务处理）
     * @param m
     * @return
     */
    @RequestMapping("/findUserDispose")
    public  String findUserDispose(Model m){
        List<SysUser> userList = sysUserService.findSysUser();
        m.addAttribute("userList",userList);
        return "CustomerService/ServiceDispose" ;
    }

    /**
     * 查询所有用户（跳转到服务反馈）
     * @param m
     * @return
     */
    @RequestMapping("/findUserResult")
    public  String findUserResult(Model m){
        List<SysUser> userList = sysUserService.findSysUser();
        m.addAttribute("userList",userList);
        return "CustomerService/ServiceResult" ;
    }

    /**
     * 查询所有用户（跳转到服务归档）
     * @param m
     * @return
     */
    @RequestMapping("/findUserDetail")
    public  String findUserDetail(Model m){
        List<SysUser> userList = sysUserService.findSysUser();
        m.addAttribute("userList",userList);
        return "CustomerService/ServiceDetail" ;
    }


    /**
     * 修改服务分配（添加指派人）
     */
    @RequestMapping("/updateCstService")
    public void updateCstService(CstService cstService,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = request.getParameter("createDate");
        cstService.setSvrCreateDate(sdf.parse(createDate));
        cstService.setSvrStatus("已分配");
        cstService.setSvrDueDate(new Date());
        int row = cstServiceService.udpateService(cstService);
        response.sendRedirect("/findUser");
    }

    /**
     * 删除服务管理记录
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping("/delCstService")
    public void delCstService(Integer id ,HttpServletResponse response) throws IOException {
        int row = cstServiceService.delService(id);
        response.sendRedirect("/findUser");
    }

    /**
     * 根据id查询服务管理信息 (跳转到服务处理页面)
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findCstServiceById")
    public String findCstServiceById(Integer id,Model m){
        CstService cstService = cstServiceService.findCstServiceById(id);
        m.addAttribute("cstService",cstService);
        return "CustomerService/ServiceDisposeDialog";
    }

    /**
     * 根据id查询服务管理信息 (跳转到服务反馈页面)
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findCstServiceByIdResult")
    public String findCstServiceByIdResult(Integer id,Model m){
        CstService cstService = cstServiceService.findCstServiceById(id);
        m.addAttribute("cstService",cstService);
        return "CustomerService/ServiceResultDialog";
    }

    /**
     * 根据id查询服务管理信息 (跳转到服务归档查看详情页面)
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findCstServiceByIdDetail")
    public String findCstServiceByIdDetail(Integer id,Model m){
        CstService cstService = cstServiceService.findCstServiceById(id);
        m.addAttribute("cstService",cstService);
        return "CustomerService/ServiceDetailDialog";
    }

    /**
     * 服务处理
     * @param cstService
     */
    @RequestMapping("/updateCstServiceDetail")
    public void updateCstServiceDetail(CstService cstService,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = request.getParameter("createDate");
        String DueDate = request.getParameter("DueDate");
        cstService.setSvrCreateDate(sdf.parse(createDate));
        cstService.setSvrDueDate(sdf.parse(DueDate));
        cstService.setSvrStatus("已处理");
        cstService.setSvrDealDate(new Date());
        int row = cstServiceService.updateCstServiceDetail(cstService);
        response.sendRedirect("/findUser");
    }

    /**
     * 服务反馈
     * @param cstService
     */
    @RequestMapping("/updateCstServiceResult")
    public void updateCstServiceResult(CstService cstService,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = request.getParameter("createDate");
        String DueDate = request.getParameter("DueDate");
        String DealDate = request.getParameter("DealDate");
        cstService.setSvrCreateDate(sdf.parse(createDate));
        cstService.setSvrDueDate(sdf.parse(DueDate));
        cstService.setSvrDealDate(sdf.parse(DealDate));
        cstService.setSvrStatus("已反馈");
        int row = cstServiceService.updateCstServiceDetail(cstService);
        response.sendRedirect("/findUserResult");
    }

    /**
     * 客户服务分析
     * @param request
     * @param m
     * @return
     */
    @RequestMapping("/findCstServiceReport")
    public String findCstServiceReport(HttpServletRequest request,Model m){
        String svrCreateDate = request.getParameter("svrCreateDate");
        List<CstService> cstServiceList = cstServiceService.findCstServiceReport(svrCreateDate);
        m.addAttribute("cstServiceList",cstServiceList);
        return "Report/ServerReport";
    }
}
