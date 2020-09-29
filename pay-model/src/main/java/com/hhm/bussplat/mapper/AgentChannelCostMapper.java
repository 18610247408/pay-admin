package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.AgentChannelCost;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface AgentChannelCostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentChannelCost record);

    int insertSelective(AgentChannelCost record);

    AgentChannelCost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentChannelCost record);

    int updateByPrimaryKey(AgentChannelCost record);

    AgentChannelCost effectiveChannelCost(@Param("query") AgentChannelCost query, @Param("time") Date time);

    AgentChannelCost find(AgentChannelCost query);
}