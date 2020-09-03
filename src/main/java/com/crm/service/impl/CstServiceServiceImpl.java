package com.crm.service.impl;

import com.crm.entity.CstService;
import com.crm.mapper.CstServiceMapper;
import com.crm.service.CstServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务管理业务逻辑实现类
 */
@Service
public class CstServiceServiceImpl implements CstServiceService {
    @Autowired
    private CstServiceMapper cstServiceMapper;

    /**
     * 添加服务管理记录（服务创建）
     * @param cstService
     * @return
     */
    @Override
    public int addCstService(CstService cstService) {
        return cstServiceMapper.insert(cstService);
    }

    /**
     * 查询所有服务管理信息
     * @return
     */
    @Override
    public List<CstService> findCstServiceAll() {
        return cstServiceMapper.selectByExample(null);
    }

    /**
     * 模糊查询
     * @param cstService
     * @return
     */
    @Override
    public List<CstService> findCstServiceByExample(CstService cstService) {
        return cstServiceMapper.mySelect(cstService);
    }

    /**
     * 根据id查询服务管理
     * @param id
     * @return
     */
    @Override
    public CstService findCstServiceById(Integer id) {
        return cstServiceMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加指派人
     * @param cstService
     * @return
     */
    @Override
    public int udpateService(CstService cstService) {
        return cstServiceMapper.updateByPrimaryKeySelective(cstService);
    }

    /**
     * 删除服务管理记录
     * @param id
     * @return
     */
    @Override
    public int delService(Integer id) {
        return cstServiceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 服务处理
     * @param cstService
     * @return
     */
    @Override
    public int updateCstServiceDetail(CstService cstService) {
        return cstServiceMapper.updateByPrimaryKeySelective(cstService);
    }

    /**
     * 服务反馈
     * @param cstService
     * @return
     */
    @Override
    public int updateCstServiceResult(CstService cstService) {
        return cstServiceMapper.updateByPrimaryKeySelective(cstService);
    }

    /**
     * 查询客户服务分析
     * @param CreateDate
     * @return
     */
    @Override
    public List<CstService> findCstServiceReport(String CreateDate) {
        return cstServiceMapper.selectServiceReport(CreateDate);
    }
}
