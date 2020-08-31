package com.crm.service.impl;

import com.crm.entity.CstCustomer;
import com.crm.mapper.CstCustomerMapper;
import com.crm.service.CstCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息管理业务逻辑实现类
 */
@Service
public class CstCustomerServiceImpl implements CstCustomerService {
    @Autowired
    private CstCustomerMapper cstCustomerMapper;

    /**
     * 查询所有客户信息
     * @return
     */
    @Override
    public List<CstCustomer> findCstCustomerAll() {
       return cstCustomerMapper.selectByExample(null);
    }

    /**
     * 模糊查询
     * @return
     */
    @Override
    public List<CstCustomer> findCstCustomerByExample(CstCustomer cstCustomer) {
        return cstCustomerMapper.mySelectByExample(cstCustomer);
    }

    /**
     * 根据id查询单个CstCustomer
     * @param id
     * @return
     */
    @Override
    public CstCustomer findCstCustomerByNo(String id) {
        return cstCustomerMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改客户信息
     * @param cstCustomer
     * @return
     */
    @Override
    public int updateCstCustomerByNo(CstCustomer cstCustomer) {
        return cstCustomerMapper.updateByPrimaryKey(cstCustomer);
    }

    /**
     * 删除客户信息
     * @param no
     * @return
     */
    @Override
    public int delCstCustomerByNo(String no) {
        return cstCustomerMapper.deleteByPrimaryKey(no);
    }

    /**
     * 查询客户构成分析
     * @return
     */
    @Override
    public List<CstCustomer> findCstCustomerMakeReport(String contribute) {
        return cstCustomerMapper.selectMakeReport(contribute);
    }


}
