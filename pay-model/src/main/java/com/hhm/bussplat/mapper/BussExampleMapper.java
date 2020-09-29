package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussExample;

import java.util.List;

public interface BussExampleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BussExample record);

    int insertSelective(BussExample record);

    BussExample selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BussExample record);

    int updateByPrimaryKey(BussExample record);

    List<BussExample> list();
}