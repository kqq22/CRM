package com.crm.controller;

import com.crm.entity.SysUser;
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

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = request.getParameter("textfield");
        String password = request.getParameter("textfield2");
        List<SysUser> userList = sysUserService.login(username,password);
        if(userList.size()>0){
            SysUser user = userList.get(0);
            String uname = user.getUserName();
            int uid = user.getUserId();
            session.setAttribute("uname",uname);
            session.setAttribute("uid",uid);
            session.setAttribute("user",user);
            return "/Main";
        }
        return "";
    }
}
