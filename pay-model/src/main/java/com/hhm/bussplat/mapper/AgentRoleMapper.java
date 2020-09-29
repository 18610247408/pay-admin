package com.hhm.bussplat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.AgentRole;

public interface AgentRoleMapper {

	int deleteByPrimaryKey(Long id);

	int insert(AgentRole record);

	int insertSelective(AgentRole record);

	AgentRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AgentRole record);

	int updateByPrimaryKey(AgentRole record);

	List<AgentRole> findOperatorRoles(@Param("operatorId") Long operatorId);

	List<AgentRole> findAllRolesOfPage(@Param("query") AgentRole query);

	List<AgentRole> allRoles(@Param("query") AgentRole query);

	List<AgentRole> checkOnlyOnlyOne(@Param("name") String name, @Param("roleCode") String roleCode);

}