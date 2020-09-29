package com.hhm.bussplat.mapper;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.AgentOperatorRole;

public interface AgentOperatorRoleMapper {
	
    int insert(AgentOperatorRole record);

    int insertSelective(AgentOperatorRole record);
    
    void deleteByOperator(@Param("operatorId") Long operatorId);
}