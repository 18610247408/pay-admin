package com.hhm.bussplat.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.agent.mapper.MapperSupport;
import com.hhm.bussplat.entity.BussJsdOrder;

@Service
public class BussJsdOrderService {

	@Autowired
	private MapperSupport mapperSupport;
	
	/**
	 * 查询订单
	 * @param order
	 * @param layPage
	 * @return
	 */
	public PageInfo<List<BussJsdOrder>> findAll(BussJsdOrder order,LayPage layPage) {
		PageHelper.startPage(layPage.getPage(),layPage.getLimit());
		List<BussJsdOrder> tempList = mapperSupport.bussJsdOrderMapper.findList(order);
        PageInfo<List<BussJsdOrder>> page = new PageInfo(tempList);
	    return page;
	}
	
	
	
	
	
}
