package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.AgentExample;

public interface AgentExampleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentExample record);

    int insertSelective(AgentExample record);

    AgentExample selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentExample record);

    int updateByPrimaryKey(AgentExample record);
}