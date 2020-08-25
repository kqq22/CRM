package com.crm.service;

import com.crm.entity.Storage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存业务逻辑接口类
 */
public interface StorageService {
    /**
     * 查询所有库存信息
     * @return 返回库存集合
     */
    public List<Storage> findStorageAll();

    /**
     * 模糊查询
     * @param prodName 查询条件
     * @param stkWarehouse 查询条件
     * @return 返回库存集合
     */
    public List<Storage> findStorageByExample(@Param("prodName")String prodName, @Param("stkWarehouse")String stkWarehouse);
}
