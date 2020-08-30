package com.crm.service;

import com.crm.entity.CstCustomer;

import java.util.List;

/**
 * 客户信息管理业务逻辑接口
 */
public interface CstCustomerService {
    /**
     * 查询所有客户信息
     * @return
     */
    public List<CstCustomer> findCstCustomerAll();

    /**
     * 模糊查询
     * @return
     */
    public List<CstCustomer> findCstCustomerByExample(CstCustomer cstCustomer);

    /**
     * 根据id查询单个CstCustomer
     * @param no
     * @return
     */
    public CstCustomer findCstCustomerByNo(String no);

    /**
     * 修改客户信息
     * @param cstCustomer
     * @return
     */
    public int updateCstCustomerByNo(CstCustomer cstCustomer);

    /**
     * 删除客户信息
     * @param no
     * @return
     */
    public int delCstCustomerByNo(String no);
}
