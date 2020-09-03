package com.crm.mapper;

import com.crm.entity.CstCustomer;
import com.crm.entity.CstCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface CstCustomerMapper {
    long countByExample(CstCustomerExample example);

    int deleteByExample(CstCustomerExample example);

    int deleteByPrimaryKey(String custNo);

    int insert(CstCustomer record);

    int insertSelective(CstCustomer record);

    List<CstCustomer> selectByExampleWithRowbounds(CstCustomerExample example, RowBounds rowBounds);

    List<CstCustomer> selectByExample(CstCustomerExample example);

    CstCustomer selectByPrimaryKey(String custNo);

    List<CstCustomer> selectMakeReport(@Param("contribute") String contribute);

    List<CstCustomer> mySelectByExample(CstCustomer record);

    List<CstCustomer> selectLost();

    List<CstCustomer> selectLostCustNo();

    int updateByExampleSelective(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByExample(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByPrimaryKeySelective(CstCustomer record);

    int updateByPrimaryKey(CstCustomer record);
}