package com.crm.service;

/**
 * 用户业务逻辑接口
 */
public interface SysUserService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 是否存在
     */
    public boolean login(String username,String password);
}
