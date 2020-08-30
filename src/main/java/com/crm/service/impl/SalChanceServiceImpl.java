package com.crm.service.impl;

import com.crm.entity.SalChance;
import com.crm.entity.SalChanceExample;
import com.crm.mapper.SalChanceMapper;
import com.crm.service.SalChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 销售机会管理业务逻辑实现类
 */
@Service
public class SalChanceServiceImpl implements SalChanceService {
    //注入Mapper
    @Autowired
    private SalChanceMapper salChanceMapper;

    /**
     * 查询所有销售机会管理信息
     * @return 返回SalChance集合
     */
    @Override
    public List<SalChance> findSalChanceAll() {
        SalChanceExample salChanceExample = new SalChanceExample();
        salChanceExample.createCriteria().andChcStatusEqualTo(1);
        return salChanceMapper.selectByExample(salChanceExample);
    }

    /**
     * 查询所有客户开发计划信息
     * @return 返回SalChance集合
     */
    @Override
    public List<SalChance> findSalManagerAll() {
        SalChanceExample salChanceExample = new SalChanceExample();
        salChanceExample.createCriteria().andChcStatusEqualTo(2);
        return salChanceMapper.selectByExample(salChanceExample);
    }

    /**
     * 销售机会管理模糊查询
     * @param salChance 查询条件
     * @return 返回SalChance集合
     */
    @Override
    public List<SalChance> findSalChanceByExample(SalChance salChance) {
        SalChanceExample salChanceExample = new SalChanceExample();
        salChanceExample.createCriteria().andChcCustNameLike(salChance.getChcCustName()).andChcLinkmanLike(salChance.getChcLinkman()).andChcTitleLike(salChance.getChcTitle()).andChcStatusEqualTo(1);
        return salChanceMapper.selectByExample(salChanceExample);
    }

    /**
     * 客户开发计划模糊查询
     * @param salChances 查询条件
     * @return 返回SalChance集合
     */
    @Override
    public List<SalChance> findSalManagerByExample(SalChance salChances) {
        SalChanceExample salChanceExample = new SalChanceExample();
        salChanceExample.createCriteria().andChcCustNameLike(salChances.getChcCustName()).andChcLinkmanLike(salChances.getChcLinkman()).andChcTitleLike(salChances.getChcTitle()).andChcStatusEqualTo(salChances.getChcStatus());
        return salChanceMapper.selectByExample(salChanceExample);
    }

    /**
     * 客户开发计划模糊查询
     * @param salChances 查询条件
     * @return 返回SalChance集合
     */
    @Override
    public List<SalChance> findSalManagerByExamples(SalChance salChances) {
        SalChanceExample salChanceExample = new SalChanceExample();
        salChanceExample.createCriteria().andChcCustNameLike(salChances.getChcCustName()).andChcLinkmanLike(salChances.getChcLinkman()).andChcTitleLike(salChances.getChcTitle()).andChcStatusNotEqualTo(salChances.getChcStatus());
        return salChanceMapper.selectByExample(salChanceExample);
    }

    /**
     * 添加销售机会管理信息
     * @param salChance
     * @return 返回是否添加成功
     */
    @Override
    public int addSalChance(SalChance salChance) {
        return salChanceMapper.insertSelective(salChance);
    }

    /**
     * 根据id删除销售机会管理记录
     * @param id
     * @return 返回是否删除成功
     */
    @Override
    public int delSalChanceById(Integer id) {
        return salChanceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改销售机会管理信息
     * @param salChance
     * @return 返回是否修改成功
     */
    @Override
    public int updateSalChanceById(SalChance salChance) {
        return salChanceMapper.updateByPrimaryKeySelective(salChance);
    }

    /**
     * 根据id查询单个SalChance
     * @param id
     * @return 返回SalChance
     */
    @Override
    public SalChance findSalChanceById(Integer id) {
        return salChanceMapper.selectByPrimaryKey(id);
    }
}
