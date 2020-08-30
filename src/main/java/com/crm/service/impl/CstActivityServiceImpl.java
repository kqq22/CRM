package com.crm.service.impl;

import com.crm.entity.CstActivity;
import com.crm.entity.CstActivityExample;
import com.crm.mapper.CstActivityMapper;
import com.crm.service.CstActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户交往记录（活动表）业务实现类
 */
@Service
public class CstActivityServiceImpl implements CstActivityService {
    @Autowired
    private CstActivityMapper cstActivityMapper;

    /**
     * 根据客户编号查询客户交往记录
     * @return
     */
    @Override
    public List<CstActivity> findCstActivityAll(String no) {
        CstActivityExample cstActivityExample = new CstActivityExample();
        cstActivityExample.createCriteria().andAtvCustNoEqualTo(no);
        return cstActivityMapper.selectByExample(cstActivityExample);
    }

    /**
     * 根据id查询客户交往记录
     * @param id
     * @return
     */
    @Override
    public CstActivity findCstActivityById(Integer id) {
        return cstActivityMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加客户交往记录
     * @param cstActivity
     * @return
     */
    @Override
    public int addActivity(CstActivity cstActivity) {
        return cstActivityMapper.insert(cstActivity);
    }

    /**
     * 修改客户交往记录
     * @param cstActivity
     * @return
     */
    @Override
    public int updateActivity(CstActivity cstActivity) {
        return cstActivityMapper.updateByPrimaryKey(cstActivity);
    }

    /**
     * 根据id删除客户交往记录
     * @param id
     * @return
     */
    @Override
    public int delActivity(Integer id) {
        return cstActivityMapper.deleteByPrimaryKey(id);
    }
}
