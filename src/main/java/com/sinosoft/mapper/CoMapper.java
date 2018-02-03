package com.sinosoft.mapper;

import com.sinosoft.pojo.Co;

public interface CoMapper {
    int deleteByPrimaryKey(String coId);

    int insert(Co record);

    int insertSelective(Co record);

    Co selectByPrimaryKey(String coId);

    int updateByPrimaryKeySelective(Co record);

    int updateByPrimaryKey(Co record);
}