package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussAgentCommission;

import java.util.List;

public interface BussAgentCommissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BussAgentCommission record);

    int insertSelective(BussAgentCommission record);

    BussAgentCommission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussAgentCommission record);

    int updateByPrimaryKey(BussAgentCommission record);

    List<BussAgentCommission> findList(BussAgentCommission query);
}