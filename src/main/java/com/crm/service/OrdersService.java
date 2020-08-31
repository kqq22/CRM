package com.crm.service;

import com.crm.entity.Orders;
import java.util.List;

/**
 * 订单业务逻辑接口类
 */
public interface OrdersService {
    /**
     * 根据客户名称查询该客户的所有订单信息
     * @param custName
     * @return
     */
    public List<Orders> findOrdersAll(String custName);

    /**
     * 客户贡献分析
     * @param customerName
     * @param odrDate
     * @return
     */
    public List<Orders> findContributeReport(String customerName,String odrDate);
}
