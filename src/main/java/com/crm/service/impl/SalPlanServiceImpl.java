package com.crm.service.impl;

import com.crm.entity.SalPlan;
import com.crm.entity.SalPlanExample;
import com.crm.mapper.SalPlanMapper;
import com.crm.service.SalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalPlanServiceImpl implements SalPlanService {
    @Autowired
    private SalPlanMapper salPlanMapper;

    /**
     * 根据销售机会id查询销售计划
     * @param id
     * @return 返回销售计划集合
     */
    @Override
    public List<SalPlan> findSalPlanBySalChanceId(Integer id) {
        SalPlanExample salPlanExample = new SalPlanExample();
        salPlanExample.createCriteria().andPlaChcIdEqualTo(id);
        return salPlanMapper.selectByExample(salPlanExample);
    }

    /**
     * 添加销售计划
     * @param salPlan
     * @return
     */
    @Override
    public int addSalPlan(SalPlan salPlan) {
        return salPlanMapper.insertSelective(salPlan);
    }

    /**
     * 根据id修改销售计划
     * @param salPlan
     * @return
     */
    @Override
    public int updateSalPlan(SalPlan salPlan) {
        System.out.println(salPlan.getPlaResult());
        return salPlanMapper.updateByPrimaryKeySelective(salPlan);
    }

    /**
     * 根据id删除销售计划
     * @param id
     * @return
     */
    @Override
    public int delSalPlan(Integer id) {
        return salPlanMapper.deleteByPrimaryKey(id);
    }
}
