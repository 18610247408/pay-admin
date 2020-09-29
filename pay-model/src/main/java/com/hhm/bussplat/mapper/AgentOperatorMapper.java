package com.hhm.bussplat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.AgentOperator;

public interface AgentOperatorMapper {
	
	int deleteByPrimaryKey(Long id);

	int insert(AgentOperator record);

	int insertSelective(AgentOperator record);

	AgentOperator selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AgentOperator record);

	int updateByPrimaryKey(AgentOperator record);

	AgentOperator findOperator(@Param("username") String username, @Param("status") String status);

	List<AgentOperator> findAllOfPage(@Param("query")AgentOperator record);
	
	AgentOperator findOperatorByRealName(@Param("realname") String realname,@Param("status")String status);
	
}
