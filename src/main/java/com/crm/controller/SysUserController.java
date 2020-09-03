package com.crm.controller;

import com.crm.entity.CstCustomer;
import com.crm.entity.CstLost;
import com.crm.entity.SysUser;
import com.crm.service.CstCustomerService;
import com.crm.service.CstLostService;
import com.crm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户分控制器
 */
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CstLostService cstLostService;

    @Autowired
    private CstCustomerService cstCustomerService;

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        //获取参数
        String username = request.getParameter("textfield");//用户名
        String password = request.getParameter("textfield2");//密码
        //调用查询方法
        List<SysUser> userList = sysUserService.login(username,password);

        if(userList.size()>0){
            SysUser user = userList.get(0);
            String uname = user.getUserName();
            int uid = user.getUserId();
            session.setAttribute("uname",uname);
            session.setAttribute("uid",uid);
            session.setAttribute("user",user);
            List<CstCustomer> cstCustomerList = cstCustomerService.findLostCustNo();
            CstCustomer cstCustomers = new CstCustomer();
            //判断是否有超过六个月没有购买行为的客户
            if (cstCustomerList.size()>0){
                for(int i=0;i<cstCustomerList.size();i++){
                    cstCustomers.setCustNo(cstCustomerList.get(i).getCustNo());
                    cstCustomers.setCustStatus("2");
                    cstCustomerService.updateCstCustomerByNo(cstCustomers);
                }
                List<CstCustomer> customerList = sysUserService.findLost();
                CstLost cstLost = new CstLost();
                for (int i=0;i<customerList.size();i++){
                    CstCustomer cstCustomer = customerList.get(i);
                    cstLost.setLstCustNo(cstCustomer.getCustNo());
                    cstLost.setLstCustName(cstCustomer.getCustName());
                    cstLost.setLstCustManagerId(cstCustomer.getCustManagerId());
                    cstLost.setLstCustManagerName(cstCustomer.getCustManagerName());
                    cstLost.setLstLastOrderDate(cstCustomer.getBmaxodrdate());
                    cstLost.setLstStatus("1");
                    int row = cstLostService.addCstLost(cstLost);
                }
            }
            return "/Main";
        }else{
            //登录失败
            session.setAttribute("errMsg", "用户名或密码错误");
            return "redirect:/login.jsp";
        }
    }
}
