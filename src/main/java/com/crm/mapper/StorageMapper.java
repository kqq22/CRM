package com.crm.mapper;

import com.crm.entity.Storage;
import com.crm.entity.StorageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageMapper {
    long countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExampleWithRowbounds(StorageExample example, RowBounds rowBounds);

    List<Storage> selectByExample(StorageExample example);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    List<Storage> myselect();

    List<Storage> myselectByExample(@Param("prodName")String prodName, @Param("stkWarehouse")String stkWarehouse);
}