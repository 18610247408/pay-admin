package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.Agent;
import com.hhm.bussplat.enums.AbleStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Agent record);

    int insertSelective(Agent record);

    Agent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Agent record);

    int updateByPrimaryKey(Agent record);

    Agent find(@Param("param1") Agent query);

    List<Agent> findList(@Param("param1")Agent query);

    void deleteByIds(@Param("param1") List<Long> ids, @Param("param2") AbleStatus ableStatus);
    
    
    Agent findByAgentNo(@Param("agentNo") String agentNo);
}