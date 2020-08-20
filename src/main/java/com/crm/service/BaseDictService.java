package com.crm.service;

import com.crm.entity.BaseDict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典管理业务逻辑接口
 */
@Service
public interface BaseDictService {
    /**
     * 查询所有数据字典内容
     * @return 数据字典集合
     */
    public List<BaseDict> findBaseDictAll();
}
