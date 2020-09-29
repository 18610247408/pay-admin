package com.hhm.bussplat.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhm.bussplat.agent.bean.resultBean.AgentInfoBean;
import com.hhm.bussplat.entity.Agent;
import com.hhm.bussplat.entity.AgentAccount;
import com.hhm.bussplat.mapper.AgentAccountMapper;
import com.hhm.bussplat.mapper.AgentMapper;

@Service
public class AgentInfoService {

	@Autowired
	private AgentMapper agentMapper;
	
	@Autowired
	private AgentAccountMapper agentAccountMapper;
	
	public AgentInfoBean queryAgentInfo(String agentNo) {
		AgentAccount account = agentAccountMapper.findByAgentNoAndStatus(agentNo, null);
		Agent agent = agentMapper.findByAgentNo(agentNo);
		
		AgentInfoBean tempBean = new AgentInfoBean();
		tempBean.setAgentNo(agentNo);
		tempBean.setCompanyName(agent.getCompanyName());
		tempBean.setCreateTime(agent.getCreateTime());
		tempBean.setIdCard(agent.getIdCard());
		tempBean.setPhoneNo(agent.getPhoneNo());
		tempBean.setRealName(agent.getRealName());
		tempBean.setBalance(account.getBalance());
		tempBean.setFreezeBalance(account.getFreezeBalance());
		tempBean.setTransitBalance(account.getTransitBalance());
		return tempBean;
	}
	
	
}
