package com.hhm.bussplat.mapper;

import com.hhm.bussplat.entity.BussAgentAccountBill;

public interface BussAgentAccountBillMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(BussAgentAccountBill record);

    int insertSelective(BussAgentAccountBill record);

    BussAgentAccountBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BussAgentAccountBill record);

    int updateByPrimaryKey(BussAgentAccountBill record);
    
   
}