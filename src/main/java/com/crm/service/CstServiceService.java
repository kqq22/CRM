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
     * 根据id查询服务管理
     * @param id
     * @return
     */
    public CstService findCstServiceById(Integer id);

    /**
     * 添加指派人
     * @param cstService
     * @return
     */
    public int udpateService(CstService cstService);

    /**
     * 删除服务管理记录
     * @param id
     * @return
     */
    public int delService(Integer id);

    /**
     * 服务处理
     * @param cstService
     * @return
     */
    public int updateCstServiceDetail(CstService cstService);

    /**
     * 服务反馈
     * @param cstService
     * @return
     */
    public int updateCstServiceResult(CstService cstService);

    /**
     * 查询客户服务分析
     * @param createDate
     * @return
     */
    public List<CstService> findCstServiceReport(String createDate);
}
