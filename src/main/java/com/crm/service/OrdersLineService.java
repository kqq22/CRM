package com.crm.service;

import com.crm.entity.OrdersLine;

import java.util.List;

/**
 * 订单详情业务逻辑接口
 */
public interface OrdersLineService {
    /**
     * 根据订单号查询订单详情
     * @param ordersId
     * @return
     */
    public List<OrdersLine> findOrdersLineAll(Integer ordersId);

}
