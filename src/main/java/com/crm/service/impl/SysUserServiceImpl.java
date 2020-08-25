package com.crm.service.impl;

import com.crm.entity.SysUserExample;
import com.crm.mapper.SysUserMapper;
import com.crm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    //注入Mapper接口
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(username).andUserPasswordEqualTo(password).andUserFlagEqualTo(1);
        return sysUserMapper.selectByExample(sysUserExample).size()>0?true:false;
    }
}
