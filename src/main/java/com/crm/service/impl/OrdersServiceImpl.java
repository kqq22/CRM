package com.crm.service.impl;
import com.crm.entity.Orders;
import com.crm.entity.OrdersExample;
import com.crm.mapper.OrdersMapper;
import com.crm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 订单业务逻辑实现类
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 根据客户名称查询该客户的所有订单信息
     * @param custName
     * @return
     */
    @Override
    public List<Orders> findOrdersAll(String custName) {
        OrdersExample ordersExample = new OrdersExample();
        ordersExample.createCriteria().andOdrCustomerEqualTo(custName);
        return ordersMapper.selectByExample(ordersExample);
    }

    /**
     * 客户贡献分析
     * @param customerName
     * @param odrDate
     * @return
     */
    @Override
    public List<Orders> findContributeReport(String customerName, String odrDate) {
        return ordersMapper.selectContributeReport(customerName,odrDate);
    }

}
