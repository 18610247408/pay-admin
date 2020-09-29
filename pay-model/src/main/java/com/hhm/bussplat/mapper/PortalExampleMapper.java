package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.PortalExample;

public interface PortalExampleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PortalExample record);

    int insertSelective(PortalExample record);

    PortalExample selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortalExample record);

    int updateByPrimaryKey(PortalExample record);
}