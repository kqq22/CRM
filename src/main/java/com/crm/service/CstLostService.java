package com.crm.service;

import com.crm.entity.CstLost;

import java.util.List;

/**
 * 客户流失业务逻辑接口类
 */
public interface CstLostService {
    /**
     * 查询你所有客户流失信息
     * @return
     */
    public List<CstLost> findCstLostAll();

    /**
     * 模糊查询
     * @param cstLost
     * @return
     */
    public List<CstLost> findCstLostByExample(CstLost cstLost);

    /**
     * 根据id查询客户流失信息
     * @param id
     * @return
     */
    public CstLost findCstLostById(Integer id);

    /**
     * 修改客户流失信息（追加暂缓流失措施，添加流失信息）
     * @param cstLost
     * @return
     */
    public int updateCstLostById(CstLost cstLost);

    /**
     * 根据状态查询已流失的客户（客户流失分析）
     * @param status
     * @return
     */
    public List<CstLost> findCstLostByStatus(String status);

    /**
     * 添加客户流失信息
     * @param cstLost 客户流失对象
     * @return
     */
    public int addCstLost(CstLost cstLost);

}
