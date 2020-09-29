package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussChannel;

public interface BussChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussChannel record);

    int insertSelective(BussChannel record);

    BussChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussChannel record);

    int updateByPrimaryKey(BussChannel record);
}