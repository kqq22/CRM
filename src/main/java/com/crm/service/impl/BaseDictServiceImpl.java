package com.crm.service.impl;

import com.crm.entity.BaseDict;
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
    @Autowired
    private BaseDictMapper baseDictMapper;

    /**
     * 查询所有数据字典内容
     * @return 数据字典集合
     */
    @Override
    public List<BaseDict> findBaseDictAll() {
        return baseDictMapper.selectByExample(null);
    }
}
