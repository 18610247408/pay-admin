package com.hhm.bussplat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.AgentMenu;

public interface AgentMenuMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AgentMenu record);

	int insertSelective(AgentMenu record);

	AgentMenu selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AgentMenu record);

	int updateByPrimaryKey(AgentMenu record);

	List<AgentMenu> allMenusOfRole(@Param("roleId") Long roleId);

	List<AgentMenu> allMenusOfOperator(@Param("operator") Long operatorId);

	List<AgentMenu> firstMenusOfOperator(@Param("operator") Long operatorId);

	List<AgentMenu> leafMenusOfOperatorAndParent(@Param("operator") Long operatorId, @Param("parentId") Long parentId);

	List<AgentMenu> findAllMenus(@Param("query") AgentMenu query);

	List<AgentMenu> allMenus(@Param("query") AgentMenu query);
}