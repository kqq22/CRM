package com.crm.service.impl;
import com.crm.entity.CstLost;
import com.crm.mapper.CstLostMapper;
import com.crm.service.CstLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户流失业务逻辑实现类
 */
@Service
public class CstLostServiceImpl implements CstLostService {
    @Autowired
    private CstLostMapper cstLostMapper;

    /**
     * 查询你所有客户流失信息
     * @return
     */
    @Override
    public List<CstLost> findCstLostAll() {
        return cstLostMapper.selectByExample(null);
    }

    /**
     * 模糊查询
     * @param cstLost
     * @return
     */
    @Override
    public List<CstLost> findCstLostByExample(CstLost cstLost) {
        return cstLostMapper.mySelect(cstLost);
    }

    /**
     * 根据id查询客户流失信息
     * @param id
     * @return
     */
    @Override
    public CstLost findCstLostById(Integer id) {
        return cstLostMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改客户流失信息（追加暂缓流失措施）
     * @param cstLost
     * @return
     */
    @Override
    public int updateCstLostById(CstLost cstLost) {
        return cstLostMapper.updateByPrimaryKey(cstLost);
    }
}
