package com.crm.mapper;

import com.crm.entity.CstService;
import com.crm.entity.CstServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CstServiceMapper {
    long countByExample(CstServiceExample example);

    int deleteByExample(CstServiceExample example);

    int deleteByPrimaryKey(Integer svrId);

    int insert(CstService record);

    int insertSelective(CstService record);

    List<CstService> selectByExampleWithRowbounds(CstServiceExample example, RowBounds rowBounds);

    List<CstService> selectByExample(CstServiceExample example);

    CstService selectByPrimaryKey(Integer svrId);

    int updateByExampleSelective(@Param("record") CstService record, @Param("example") CstServiceExample example);

    int updateByExample(@Param("record") CstService record, @Param("example") CstServiceExample example);

    int updateByPrimaryKeySelective(CstService record);

    int updateByPrimaryKey(CstService record);
}