package com.crm.service;

import com.crm.entity.CstActivity;

import java.util.List;

/**
 * 客户交往记录（活动表）业务逻辑接口类
 */
public interface CstActivityService {
    /**
     * 根据客户编号查询客户交往记录
     * @return
     */
    public List<CstActivity> findCstActivityAll(String no);

    /**
     * 根据id查询客户交往记录
     * @param id
     * @return
     */
    public CstActivity findCstActivityById(Integer id);

    /**
     * 添加客户交往记录
     * @param cstActivity
     * @return
     */
    public int addActivity(CstActivity cstActivity);

    /**
     * 修改客户交往记录
     * @param cstActivity
     * @return
     */
    public int updateActivity(CstActivity cstActivity);

    /**
     * 根据id删除客户交往记录
     * @param id
     * @return
     */
    public int delActivity(Integer id);
}
