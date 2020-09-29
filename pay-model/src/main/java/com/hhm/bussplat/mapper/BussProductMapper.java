package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussProduct;

public interface BussProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussProduct record);

    int insertSelective(BussProduct record);

    BussProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussProduct record);

    int updateByPrimaryKey(BussProduct record);
}