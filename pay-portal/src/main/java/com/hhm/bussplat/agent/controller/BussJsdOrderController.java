package com.hhm.bussplat.agent.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.agent.bean.LayResult;
import com.hhm.bussplat.agent.service.BussJsdOrderService;
import com.hhm.bussplat.entity.BussJsdOrder;
import com.hhm.bussplat.util.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("jsd")
public class BussJsdOrderController extends BasicController {

	@Autowired
	private BussJsdOrderService bussJsdOrderService;
	
	@RequestMapping("/toQuery.do")
	public String toIndexPage() {
		return "order/jsdOrderQuery";
	}
	
	@RequestMapping(value = "/findAll.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String findAll(LayPage page,BussJsdOrder order) {
		order.setAgentNo(getAgentNo());
		log.info("jsd order findall param = {}",JsonUtil.toJson(order));
		PageInfo<List<BussJsdOrder>> result = bussJsdOrderService.findAll(order, page);
		return JsonUtil.toJson(LayResult.success(result.getTotal(),result.getList()));
	}
	

}
