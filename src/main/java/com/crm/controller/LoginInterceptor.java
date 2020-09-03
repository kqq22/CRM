package com.crm.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //获取session
         Object user = request.getSession().getAttribute("user");
         if(user==null||user==""){
             //继续回到登录界面
             response.sendRedirect("/login.jsp");
             return false;//拦截
         }else{
             return true;//放行
         }
    }
}
