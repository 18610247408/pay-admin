package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussCategory;

public interface BussCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussCategory record);

    int insertSelective(BussCategory record);

    BussCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussCategory record);

    int updateByPrimaryKey(BussCategory record);
}