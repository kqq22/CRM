package com.crm.service;

import com.crm.entity.SalPlan;

import java.util.List;

public interface SalPlanService {
    /**
     * 根据销售机会id查询销售计划
     * @param id
     * @return 返回销售计划集合
     */
    public List<SalPlan> findSalPlanBySalChanceId(Integer id);

    /**
     * 添加销售计划
     * @param salPlan
     * @return
     */
    public int addSalPlan(SalPlan salPlan);

    /**
     * 根据id修改销售计划
     * @param salPlan
     * @return
     */
    public int updateSalPlan(SalPlan salPlan);

    /**
     * 根据id删除销售计划
     * @param id
     * @return
     */
    public int delSalPlan(Integer id);
}
