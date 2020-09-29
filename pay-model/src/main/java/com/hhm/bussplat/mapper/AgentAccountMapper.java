package com.hhm.bussplat.mapper;

import org.apache.ibatis.annotations.Param;

import com.hhm.bussplat.entity.AgentAccount;

public interface AgentAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgentAccount record);

    int insertSelective(AgentAccount record);

    AgentAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentAccount record);

    int updateByPrimaryKey(AgentAccount record);
    
    int updateWithVersion(AgentAccount record);
    
    AgentAccount findByAgentNoAndStatus(@Param("agentNo")String agentNo,@Param("status")String status);
}