package com.crm.service;

import com.crm.entity.SalChance;
import java.util.List;

/**
 * 销售机会管理业务逻辑接口类
 */
public interface SalChanceService {
    /**
     * 查询所有销售机会管理信息
     * @return 返回SalChance集合
     */
    public List<SalChance> findSalChanceAll();

    /**
     * 查询所有客户开发计划信息
     * @return 返回SalChance集合
     */
    public List<SalChance> findSalManagerAll();

    /**
     * 销售机会管理模糊查询
     * @param salChance 查询条件
     * @return 返回SalChance集合
     */
    public List<SalChance> findSalChanceByExample(SalChance salChance);

    /**
     * 客户开发计划模糊查询，查询指定开发状态
     * @param salChances 查询条件
     * @return 返回SalChance集合
     */
    public List<SalChance> findSalManagerByExample(SalChance salChances);

    /**
     * 客户开发计划模糊查询，查询全部
     * @param salChances 查询条件
     * @return 返回SalChance集合
     */
    public List<SalChance> findSalManagerByExamples(SalChance salChances);

    /**
     * 添加销售机会管理信息
     * @param salChance
     * @return 返回是否添加成功
     */
    public int addSalChance(SalChance salChance);

    /**
     * 根据id删除销售机会管理记录
     * @param id
     * @return 返回是否删除成功
     */
    public int delSalChanceById(Integer id);

    /**
     * 根据id修改销售机会管理信息
     * @param salChance
     * @return 返回是否修改成功
     */
    public int updateSalChanceById(SalChance salChance);

    /**
     * 根据id查询单个SalChance
     * @param id
     * @return 返回SalChance
     */
    public SalChance findSalChanceById(Integer id);
}
