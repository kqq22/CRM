package com.crm.service.impl;

import com.crm.entity.Storage;
import com.crm.mapper.StorageMapper;
import com.crm.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存业务逻辑实现类
 */
@Service
public class StorageServiceImpl implements StorageService {
    //
    @Autowired
    private StorageMapper storageMapper;

    /**
     * 查询所有库存信息
     * @return 返回库存集合
     */
    @Override
    public List<Storage> findStorageAll() {
        return storageMapper.myselect();
    }

    /**
     * 模糊查询
     * @param prodName 查询条件
     * @param stkWarehouse 查询条件
     * @return 返回库存集合
     */
    @Override
    public List<Storage> findStorageByExample(String prodName ,String stkWarehouse) {
        return storageMapper.myselectByExample(prodName,stkWarehouse);
    }
}
