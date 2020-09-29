package com.hhm.bussplat.agent.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhm.bussplat.agent.bean.resultBean.AgentInfoBean;
import com.hhm.bussplat.agent.service.AgentInfoService;


@Controller
@RequestMapping("agent")
public class AgentInfoController extends BasicController {

	@Autowired
	private AgentInfoService agentInfoService;
	
	
	@RequestMapping("/toQuery.do")
	public String toIndexPage(HttpServletRequest request) {
		AgentInfoBean tempBean = agentInfoService.queryAgentInfo(getAgentNo());
		request.setAttribute("agentInfo", tempBean);
		return "agent/info";
	}
	
	
	
}
