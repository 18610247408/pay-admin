package com.hhm.bussplat.agent.mapper;

import com.hhm.bussplat.mapper.BussJsdOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author q
 * @time 2020/5/15 9:55 下午
 */
@Component
public class MapperSupport {

	
	@Autowired
	public BussJsdOrderMapper bussJsdOrderMapper;
	
}
