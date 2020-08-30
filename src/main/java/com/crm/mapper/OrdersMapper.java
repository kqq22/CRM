package com.crm.mapper;

import com.crm.entity.Orders;
import com.crm.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExampleWithRowbounds(OrdersExample example, RowBounds rowBounds);

    List<Orders> selectByExample(OrdersExample example);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);
}