package com.crm.service;

import com.crm.entity.BaseDict;
import java.util.List;

/**
 * 数据字典管理业务逻辑接口
 */
public interface BaseDictService {
    /**
     * 查询所有数据字典内容
     * @return 返回数据字典集合
     */
    public List<BaseDict> findBaseDictAll();

    /**
     * 模糊查询
     * @param baseDict 查询条件
     * @return 返回数据字典集合
     */
    public List<BaseDict> findBaseDictByExample(BaseDict baseDict);

    /**
     * 添加数据字典数据
     * @param baseDict 数据字典对象
     * @return 返回是否添加成功
     */
    public int addBaseDict(BaseDict baseDict);

    /**
     * 根据id删除一条数据字典记录
     * @param id
     * @return
     */
    public int delBaseDict(Integer id);

    /**
     * 修改数据字典
     * @param baseDict
     * @return
     */
    public int updateBaseDict(BaseDict baseDict);

    public BaseDict findBaseDictById(Integer id);
}
