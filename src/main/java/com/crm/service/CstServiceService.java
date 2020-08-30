package com.crm.service;

import com.crm.entity.CstService;

import java.util.List;

/**
 * 服务管理业务逻辑接口类
 */
public interface CstServiceService {
    /**
     * 添加服务管理记录（服务创建）
     * @param cstService
     * @return
     */
    public int addCstService(CstService cstService);

    /**
     * 查询所有服务管理信息
     * @return
     */
    public List<CstService> findCstServiceAll();

    /**
     * 模糊查询
     * @param cstService
     * @return
     */
    public List<CstService> findCstServiceByExample(CstService cstService);

    /**
     * 添加指派人
     * @param cstService
     * @return
     */
    public int udpateService(CstService cstService);
}
