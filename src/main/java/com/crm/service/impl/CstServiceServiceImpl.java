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
        System.out.println(cstService.getSvrCustName());
        return cstServiceMapper.mySelect(cstService);
    }

    /**
     * 添加指派人
     * @param cstService
     * @return
     */
    @Override
    public int udpateService(CstService cstService) {
        return cstServiceMapper.updateByPrimaryKey(cstService);
    }
}
