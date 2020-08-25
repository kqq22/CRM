package com.crm.service.impl;

import com.crm.entity.Product;
import com.crm.entity.ProductExample;
import com.crm.mapper.ProductMapper;
import com.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品信息业务逻辑实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    //注入Mapper接口
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有产品信息
     * @return 产品信息集合
     */
    @Override
    public List<Product> findProductAll() {
        return productMapper.selectByExample(null);
    }

    /**
     * 模糊查询
     * @param product 查询条件
     * @return
     */
    @Override
    public List<Product> findProductByExample(Product product) {
        ProductExample productExample = new ProductExample();
        //设置查询条件
        productExample.createCriteria().andProdNameLike(product.getProdName()).andProdTypeLike(product.getProdType()).andProdBatchLike(product.getProdBatch());
        return productMapper.selectByExample(productExample);
    }
}
