package com.crm.controller;

import com.crm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
        String username = request.getParameter("textfield");
        String password = request.getParameter("textfield2");
        boolean flag = sysUserService.login(username,password);
        if(flag){
            return "/Main";
        }
        return "";
    }
}
