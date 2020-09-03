package com.crm.service.impl;

import com.crm.entity.CstCustomer;
import com.crm.entity.SysUser;
import com.crm.entity.SysUserExample;
import com.crm.mapper.CstCustomerMapper;
import com.crm.mapper.SysUserMapper;
import com.crm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务逻辑实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    //注入Mapper接口
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private CstCustomerMapper cstCustomerMapper;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public List<SysUser> login(String username, String password) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(username).andUserPasswordEqualTo(password).andUserFlagEqualTo(1);
        return sysUserMapper.selectByExample(sysUserExample);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<SysUser> findSysUser() {
        return sysUserMapper.selectByExample(null);
    }

    /**
     * 查询将要流失的客户
     * @return
     */
    @Override
    public List<CstCustomer> findLost() {
        return cstCustomerMapper.selectLost();
    }
}
