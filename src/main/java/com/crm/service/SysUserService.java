package com.crm.service;

import com.crm.entity.SysUser;

import java.util.List;

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
    public List<SysUser> login(String username, String password);

    /**
     * 查询所有用户
     * @return
     */
    public List<SysUser> findSysUser();
}
