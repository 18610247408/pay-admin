package com.hhm.bussplat.mapper;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.AgentRoleMenu;

public interface AgentRoleMenuMapper 
{
    int insert(AgentRoleMenu record);

    int insertSelective(AgentRoleMenu record);
    
    void delete(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}