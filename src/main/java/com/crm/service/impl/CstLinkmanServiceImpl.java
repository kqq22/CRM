package com.crm.service.impl;

import com.crm.entity.CstLinkman;
import com.crm.entity.CstLinkmanExample;
import com.crm.mapper.CstLinkmanMapper;
import com.crm.service.CstLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系人业务逻辑实现类
 */
@Service
public class CstLinkmanServiceImpl implements CstLinkmanService {
    @Autowired
    private CstLinkmanMapper cstLinkmanMapper;

    /**
     * 根据客户信息编号查询
     * @param no
     * @return 返回CstLinkman集合
     */
    @Override
    public List<CstLinkman> findCstLinkmanByNo(String no) {
        CstLinkmanExample cstLinkmanExample = new CstLinkmanExample();
        cstLinkmanExample.createCriteria().andLkmCustNoEqualTo(no);
        return cstLinkmanMapper.selectByExample(cstLinkmanExample);
    }

    /**
     * 根据id查询联系人
     * @param id
     * @return
     */
    @Override
    public CstLinkman findCstLinkmanById(Integer id) {
        return cstLinkmanMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加联系人
     * @param cstLinkman
     * @return
     */
    @Override
    public int addCstLinkman(CstLinkman cstLinkman) {
        return cstLinkmanMapper.insert(cstLinkman);
    }

    /**
     * 修改联系人信息
     * @return
     */
    @Override
    public int updateLinkman(CstLinkman cstLinkman) {
        return cstLinkmanMapper.updateByPrimaryKey(cstLinkman);
    }

    /**
     * 删除联系人
     * @param id
     * @return
     */
    @Override
    public int delLinkman(Integer id) {
        return cstLinkmanMapper.deleteByPrimaryKey(id);
    }

}
