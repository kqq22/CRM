package com.crm.mapper;

import com.crm.entity.OrdersLine;
import com.crm.entity.OrdersLineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersLineMapper {
    long countByExample(OrdersLineExample example);

    int deleteByExample(OrdersLineExample example);

    int insert(OrdersLine record);

    int insertSelective(OrdersLine record);

    List<OrdersLine> selectByExampleWithRowbounds(OrdersLineExample example, RowBounds rowBounds);

    List<OrdersLine> selectByExample(OrdersLineExample example);

    List<OrdersLine> mySelect(Integer orderId);

    int updateByExampleSelective(@Param("record") OrdersLine record, @Param("example") OrdersLineExample example);

    int updateByExample(@Param("record") OrdersLine record, @Param("example") OrdersLineExample example);
}