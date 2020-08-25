package com.crm.service.impl;

import com.crm.entity.BaseDict;
import com.crm.entity.BaseDictExample;
import com.crm.mapper.BaseDictMapper;
import com.crm.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 数据字典管理业务逻辑接口实现类
 */
@Service
public class BaseDictServiceImpl implements BaseDictService {
    //注入Mapper接口
    @Autowired
    private BaseDictMapper baseDictMapper;

    /**
     * 查询所有数据字典内容
     * @return 返回数据字典集合
     */
    @Override
    public List<BaseDict> findBaseDictAll() {
        return baseDictMapper.selectByExample(null);
    }

    /**
     * 模糊查询
     * @param baseDict 查询条件
     * @return 返回数据字典集合
     */
    @Override
    public List<BaseDict> findBaseDictByExample(BaseDict baseDict) {
        BaseDictExample baseDictExample = new BaseDictExample();
        //设置查询条件
        baseDictExample.createCriteria().andDictTypeLike(baseDict.getDictType()).andDictItemLike(baseDict.getDictItem()).andDictValueLike(baseDict.getDictValue());
        return baseDictMapper.selectByExample(baseDictExample);
    }

    /**
     * 添加数据字典数据
     * @param baseDict 数据字典对象
     * @return 返回是否添加成功
     */
    @Override
    public int addBaseDict(BaseDict baseDict) {
        return baseDictMapper.insertSelective(baseDict);
    }

    /**
     * 根据id删除一条数据字典记录
     * @param id
     * @return
     */
    @Override
    public int delBaseDict(Integer id) {
        return baseDictMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改数据字典
     * @param baseDict
     * @return
     */
    @Override
    public int updateBaseDict(BaseDict baseDict) {
        return baseDictMapper.updateByPrimaryKeySelective(baseDict);
    }

    /**
     * 根据id查询单个BaseDict
     * @param id
     * @return
     */
    @Override
    public BaseDict findBaseDictById(Integer id) {
        return baseDictMapper.selectByPrimaryKey(id);
    }
}
