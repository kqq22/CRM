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
     * 分页查询+模糊查询
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
     * 查询所有用户
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
     * 修改服务分配（添加指派人）
     */
    @RequestMapping("/updateCstService")
    public void updateCstService(CstService cstService,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = request.getParameter("createDate");
        System.out.println(cstService.getSvrType());
        cstService.setSvrCreateDate(sdf.parse(createDate));
        cstService.setSvrStatus("已分配");
        cstService.setSvrDueDate(new Date());
        int row = cstServiceService.udpateService(cstService);
        System.out.println(row);
        response.sendRedirect("/findUser");
    }
}
