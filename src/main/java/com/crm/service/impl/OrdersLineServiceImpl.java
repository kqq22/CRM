package com.crm.service.impl;

import com.crm.entity.OrdersLine;
import com.crm.entity.OrdersLineExample;
import com.crm.mapper.OrdersLineMapper;
import com.crm.service.OrdersLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单详情业务逻辑实现类
 */
@Service
public class OrdersLineServiceImpl implements OrdersLineService {
    @Autowired
    private OrdersLineMapper ordersLineMapper;

    /**
     * 根据订单号查询订单详情
     * @param ordersId
     * @return
     */
    @Override
    public List<OrdersLine> findOrdersLineAll(Integer ordersId) {
        return ordersLineMapper.mySelect(ordersId);
    }
}
