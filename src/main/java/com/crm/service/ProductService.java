package com.crm.service;
import com.crm.entity.Product;
import java.util.List;

/**
 * 产品信息业务逻辑接口类
 */
public interface ProductService {
    /**
     * 查询所有产品信息
     * @return 产品信息集合
     */
    public List<Product> findProductAll();

    /**
     * 模糊查询
     * @param product 查询条件
     * @return
     */
    public List<Product> findProductByExample(Product product);
}
